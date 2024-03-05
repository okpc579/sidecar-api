package org.kpaas.sidecar.portal.api.controller;

import org.cloudfoundry.client.v3.organizations.*;
import org.kpaas.sidecar.portal.api.common.Common;
import org.kpaas.sidecar.portal.api.model.Organization;
import org.kpaas.sidecar.portal.api.service.OrganizationsServiceV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrganizationsController extends Common {
    @Autowired
    private OrganizationsServiceV3 organizationsServiceV3;

    @PostMapping(value = {"/organizations/create"})
    public CreateOrganizationResponse create(@RequestBody Organization org, String token) throws Exception {
        return organizationsServiceV3.create(org, token);
    }

    @DeleteMapping(value = {"/organizations/{orgGuid}/delete"})
    public String delete(@PathVariable String orgGuid, String token) throws Exception {
        return organizationsServiceV3.delete(orgGuid, token);
    }

    @GetMapping(value = {"/organizations/{orgGuid}/get"})
    public GetOrganizationResponse get(@PathVariable String orgGuid, String token) throws Exception {
        return organizationsServiceV3.get(orgGuid, token);
    }

    @GetMapping(value = {"/organizations/list"})
    public ListOrganizationsResponse list(String token) throws Exception {
        return organizationsServiceV3.list(token);
    }

    @GetMapping(value = {"/organizations/{orgGuid}/listDomains"})
    public ListOrganizationDomainsResponse listDomains(@PathVariable String orgGuid, String token) throws Exception {
        return organizationsServiceV3.listDomains(orgGuid, token);
    }

    @GetMapping(value = {"/organizations/{orgGuid}/getDefaultDomain"})
    public GetOrganizationDefaultDomainResponse getDefaultDomain(@PathVariable String orgGuid, String token) throws Exception {
        return organizationsServiceV3.getDefaultDomain(orgGuid, token);
    }
}