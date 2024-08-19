/*package com.retailsearch.retailsearch.importer

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.*;

class importUserEvent {
    public static void main(String[] args) throws Exception {
        String projectId = "sinuous-anvil-432407-m4";
        String gcsUri = "gs://retailsearch/event.json";

        CatalogName parent = CatalogName.of(projectId, "global", "default_catalog");
        GcsSource gcsSource = GcsSource.newBuilder().addInputUris(gcsUri).build();
        UserEventInputConfig inputConfig = UserEventInputConfig.newBuilder().setGcsSource(gcsSource).build();

        try (UserEventServiceClient client = UserEventServiceClient.create()) {
            OperationFuture<ImportUserEventsResponse, ImportMetadata> response = client.importUserEventsAsync(parent, inputConfig);
            System.out.println("User events import initiated: " + response.getName());
        }
    }
}*/