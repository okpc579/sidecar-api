package org.kpaas.sidecar.portal.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.cloudfoundry.AllowNulls;
import org.cloudfoundry.Nullable;
import org.cloudfoundry.client.v3.Link;
import org.cloudfoundry.client.v3.Metadata;
import org.cloudfoundry.client.v3.domains.DomainRelationships;
import org.cloudfoundry.client.v3.domains.RouterGroup;
import org.cloudfoundry.client.v3.routes.Protocol;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize
public class Domain extends org.cloudfoundry.client.v3.domains.Domain {


    @JsonProperty("metadata")
    @Nullable
    public Metadata metadata;

    @JsonProperty("name")
    public String name;

    @JsonProperty("relationships")
    @Nullable
    public DomainRelationships relationships;

    @AllowNulls
    @JsonProperty("router_group")
    @Nullable
    public RouterGroup routerGroup;

    @JsonProperty("supported_protocols")
    @Nullable
    public List<Protocol> supportedProtocols;

    @JsonProperty("internal")
    @Nullable
    public boolean isInternal;


    @JsonProperty("created_at")
    public String createdAt;
    @JsonIgnore
    @JsonProperty("id")
    public String id;
    @JsonProperty("links")
    public Map<String, Link> links;
    @JsonProperty("updated_at")
    public String updatedAt;


    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DomainRelationships getRelationships() {
        return relationships;
    }

    public void setRelationships(DomainRelationships relationships) {
        this.relationships = relationships;
    }

    @Override
    public RouterGroup getRouterGroup() {
        return routerGroup;
    }

    public void setRouterGroup(RouterGroup routerGroup) {
        this.routerGroup = routerGroup;
    }

    @Override
    public List<Protocol> getSupportedProtocols() {
        return supportedProtocols;
    }

    public void setSupportedProtocols(List<Protocol> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }

    @Override
    public boolean isInternal() {
        return isInternal;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    @Override
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }

    @Override
    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Domain{" +
                "metadata=" + metadata +
                ", name='" + name + '\'' +
                ", relationships=" + relationships +
                ", routerGroup=" + routerGroup +
                ", supportedProtocols=" + supportedProtocols +
                ", isInternal=" + isInternal +
                ", createdAt='" + createdAt + '\'' +
                ", id='" + id + '\'' +
                ", links=" + links +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
