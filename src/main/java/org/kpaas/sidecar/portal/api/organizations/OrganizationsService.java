package org.kpaas.sidecar.portal.api.organizations;

//import org.container.platform.api.common.*;
import org.container.platform.api.common.CommonService;
import org.container.platform.api.common.ResultStatusService;
import org.container.platform.api.common.model.CommonResourcesYaml;
import org.container.platform.api.common.model.Params;
import org.container.platform.api.common.model.ResultStatus;
import org.container.platform.api.users.Users;
import org.container.platform.api.users.UsersList;
import org.container.platform.api.users.UsersService;
import org.kpaas.sidecar.portal.api.common.Constants;
import org.kpaas.sidecar.portal.api.common.SidecarPropertyService;
import org.kpaas.sidecar.portal.api.common.SidecarRestTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * Roles Service 클래스
 *
 * @author hkm
 * @version 1.0
 * @since 2022.05.24
 */
@Service
public class OrganizationsService {
    @Qualifier("sidecarRestTemplateService")
    private final SidecarRestTemplateService restTemplateService;
    private final CommonService commonService;
    @Qualifier("sidecarPropertyService")
    private final SidecarPropertyService propertyService;
    private final ResultStatusService resultStatusService;
    private final UsersService usersService;


    /**
     * Instantiates a new Roles service
     *
     * @param restTemplateService the rest template service
     * @param commonService       the common service
     * @param propertyService     the property service
     */
    @Autowired
    public OrganizationsService(SidecarRestTemplateService restTemplateService, CommonService commonService, SidecarPropertyService propertyService, ResultStatusService resultStatusService, UsersService usersService) {
        this.restTemplateService = restTemplateService;
        this.commonService = commonService;
        this.propertyService = propertyService;
        this.resultStatusService = resultStatusService;
        this.usersService = usersService;
    }


    /**
     * Roles 목록 조회(Get Roles list)
     *
     * @param params the params
     * @return the roles list
     */
    public Object getOrganizationsList(Params params) {
        Object result =  restTemplateService.send(org.kpaas.sidecar.portal.api.common.Constants.TARGET_SIDECAR_API,
                "/v3/organizations", HttpMethod.GET, null, Map.class, params);

        return result;
    }


    /**
     * Roles 상세 조회(Get Roles detail)
     *
     * @param params the params
     * @return the roles
     */
    public org.kpaas.sidecar.portal.api.organizations.Roles getRoles(Params params) {
        HashMap responseMap = (HashMap) restTemplateService.send(Constants.TARGET_CP_MASTER_API,
                propertyService.getCpMasterApiListRolesGetUrl(), HttpMethod.GET, null, Map.class, params);
        org.kpaas.sidecar.portal.api.organizations.Roles roles = commonService.setResultObject(responseMap, org.kpaas.sidecar.portal.api.organizations.Roles.class);
        roles = commonService.annotationsProcessing(roles, org.kpaas.sidecar.portal.api.organizations.Roles.class);
        return (org.kpaas.sidecar.portal.api.organizations.Roles) commonService.setResultModel(roles, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Roles YAML 조회(Get Roles yaml)
     *
     * @param params the params
     * @return the roles yaml
     */
    public CommonResourcesYaml getRolesYaml(Params params) {
        String resourceYaml = restTemplateService.send(Constants.TARGET_CP_MASTER_API,
                propertyService.getCpMasterApiListRolesGetUrl(), HttpMethod.GET, null, String.class, Constants.ACCEPT_TYPE_YAML, params);
        return (CommonResourcesYaml) commonService.setResultModel(new CommonResourcesYaml(resourceYaml), Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Roles 생성(Create Roles)
     *
     * @param params the params
     * @return return is succeeded
     */
    public ResultStatus createRoles(Params params) {
        ResultStatus resultStatus = restTemplateService.sendYaml(Constants.TARGET_CP_MASTER_API,
                propertyService.getCpMasterApiListRolesCreateUrl(), HttpMethod.POST, ResultStatus.class, params);
        return (ResultStatus) commonService.setResultModel(resultStatus, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Roles 삭제(Delete Roles)
     *
     * @param params the params
     * @return return is succeeded
     */
    public ResultStatus deleteRoles(Params params) {
        if (propertyService.getRolesList().contains(params.getResourceName())) {
            return resultStatusService.DO_NOT_DELETE_DEFAULT_RESOURCES();
        }

        ResultStatus resultStatus = restTemplateService.send(Constants.TARGET_CP_MASTER_API,
                propertyService.getCpMasterApiListRolesDeleteUrl(), HttpMethod.DELETE, null, ResultStatus.class, params);
        return (ResultStatus) commonService.setResultModel(resultStatus, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * Roles 수정(Update Roles)
     *
     * @param params the params
     * @return return is succeeded
     */
    public ResultStatus updateRoles(Params params) {
        ResultStatus resultStatus = restTemplateService.sendYaml(Constants.TARGET_CP_MASTER_API,
                propertyService.getCpMasterApiListRolesUpdateUrl(), HttpMethod.PUT, ResultStatus.class, params);
        return (ResultStatus) commonService.setResultModel(resultStatus, Constants.RESULT_STATUS_SUCCESS);
    }


    /**
     * User 가 속해 있는 Namespace 와 Role 목록 조회(Get Namespace and Roles List to which User belongs)
     *
     * @param params the params
     * @return return is succeeded
     */
    public Object getNamespacesRolesTemplateList(Params params) {
        params.setNamespace(Constants.ALL_NAMESPACES);
        org.kpaas.sidecar.portal.api.organizations.RolesList rolesLIst = null; //getRolesList(params);
        UsersList usersList = usersService.getMappingNamespacesListByUser(params);

        for (org.kpaas.sidecar.portal.api.organizations.RolesListItem item : rolesLIst.getItems()) {
            for (Users user : usersList.getItems()) {
                if (user.getCpNamespace().equals(item.getNamespace()) && user.getRoleSetCode().equals(item.getName())) {
                    item.setCheckYn(Constants.CHECK_Y);
                }
            }
        }

        rolesLIst = commonService.resourceListProcessing(rolesLIst, params, org.kpaas.sidecar.portal.api.organizations.RolesList.class);
        return commonService.setResultModel(rolesLIst, Constants.RESULT_STATUS_SUCCESS);
    }
}
