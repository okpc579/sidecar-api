package org.kpaas.sidecar.portal.api.controller;

import org.cloudfoundry.client.v3.buildpacks.ListBuildpacksResponse;
import org.kpaas.sidecar.portal.api.service.BuildpacksServiceV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildpacksController {
    @Autowired
    private BuildpacksServiceV3 buildpacksServiceV3;

    @GetMapping(value = {"/buildpacks/list"})
    public ListBuildpacksResponse list(String token) throws Exception {
        return buildpacksServiceV3.list(token);
    }
}
