package org.kpaas.sidecar.portal.api.controller;

import org.cloudfoundry.client.v3.domains.CreateDomainResponse;
import org.cloudfoundry.client.v3.domains.GetDomainResponse;
import org.cloudfoundry.client.v3.domains.ListDomainsResponse;
import org.kpaas.sidecar.portal.api.model.Domain;
import org.kpaas.sidecar.portal.api.service.DomainsServiceV3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DomainsController {
    @Autowired
    private DomainsServiceV3 domainsServiceV3;

    @PostMapping(value = {"/domains"})
    public CreateDomainResponse create(@RequestBody Domain domain) throws Exception {
        return domainsServiceV3.create(domain);
    }

    @DeleteMapping(value = {"/domains/{domainGuid}"})
    public String delete(@PathVariable String domainGuid) throws Exception {
        return domainsServiceV3.delete(domainGuid);
    }

    @GetMapping(value = {"/domains/{domainGuid}/get"})
    public GetDomainResponse get(@PathVariable String domainGuid) throws Exception {
        return domainsServiceV3.get(domainGuid);
    }

    @GetMapping(value = {"/domains/list"})
    public ListDomainsResponse list(@RequestParam(required = false) List<String> names, @RequestParam(required = false) List<String> owningOrgGuids) throws Exception {
        return domainsServiceV3.list(names, owningOrgGuids);
    }
}
