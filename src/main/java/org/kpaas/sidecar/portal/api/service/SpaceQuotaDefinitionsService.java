package org.kpaas.sidecar.portal.api.service;

import org.cloudfoundry.client.v2.spacequotadefinitions.*;
import org.kpaas.sidecar.portal.api.common.Common;

public class SpaceQuotaDefinitionsService extends Common {
    public AssociateSpaceQuotaDefinitionResponse associateSpace(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().associateSpace(AssociateSpaceQuotaDefinitionRequest.builder().build()).block();
    }

    public CreateSpaceQuotaDefinitionResponse create(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().create(CreateSpaceQuotaDefinitionRequest.builder().build()).block();
    }

    public DeleteSpaceQuotaDefinitionResponse delete(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().delete(DeleteSpaceQuotaDefinitionRequest.builder().build()).block();
    }

    public GetSpaceQuotaDefinitionResponse get(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().get(GetSpaceQuotaDefinitionRequest.builder().build()).block();
    }

    public ListSpaceQuotaDefinitionsResponse list(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().list(ListSpaceQuotaDefinitionsRequest.builder().build()).block();
    }

    public ListSpaceQuotaDefinitionSpacesResponse listSpaces(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().listSpaces(ListSpaceQuotaDefinitionSpacesRequest.builder().build()).block();
    }

    public Void removeSpace(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().removeSpace(RemoveSpaceQuotaDefinitionRequest.builder().build()).block();
    }

    public UpdateSpaceQuotaDefinitionResponse update(String guid) {
        return cloudFoundryClient(tokenProvider()).spaceQuotaDefinitions().update(UpdateSpaceQuotaDefinitionRequest.builder().build()).block();
    }
}
