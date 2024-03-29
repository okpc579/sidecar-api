package org.kpaas.sidecar.portal.api.service;

import org.cloudfoundry.client.v2.blobstores.DeleteBlobstoreBuildpackCachesRequest;
import org.cloudfoundry.client.v2.blobstores.DeleteBlobstoreBuildpackCachesResponse;
import org.kpaas.sidecar.portal.api.common.Common;

public class BlobstoresService extends Common {
    public DeleteBlobstoreBuildpackCachesResponse deleteBuildpackCaches(String guid) {
        return cloudFoundryClient(tokenProvider()).blobstores().deleteBuildpackCaches(DeleteBlobstoreBuildpackCachesRequest.builder().build()).block();
    }
}
