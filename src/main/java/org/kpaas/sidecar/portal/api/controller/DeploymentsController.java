package org.kpaas.sidecar.portal.api.controller;

import org.cloudfoundry.client.v3.deployments.CancelDeploymentResponse;
import org.cloudfoundry.client.v3.deployments.GetDeploymentResponse;
import org.cloudfoundry.client.v3.deployments.ListDeploymentsResponse;
import org.kpaas.sidecar.portal.api.service.DeploymentsServiceV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("sDeploymentsController")
public class DeploymentsController {
    @Autowired
    private DeploymentsServiceV3 deploymentsServiceV3;

    @PostMapping(value = {"/deployments/{deploymentGuid}/cancel"})
    public CancelDeploymentResponse cancel(@PathVariable String deploymentGuid) throws Exception {
        return deploymentsServiceV3.cancel(deploymentGuid);
    }

    @GetMapping(value = {"/deployments/{deploymentGuid}/get"})
    public GetDeploymentResponse get(@PathVariable String deploymentGuid) throws Exception {
        return deploymentsServiceV3.get(deploymentGuid);
    }

    @GetMapping(value = {"/deployments/list"})
    public ListDeploymentsResponse list() throws Exception {
        return deploymentsServiceV3.list();
    }
}
