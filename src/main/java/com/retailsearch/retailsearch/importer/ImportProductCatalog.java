/*package com.retailsearch.retailsearch.importer;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.retail.v2.CatalogName;
import com.google.cloud.retail.v2.GcsSource;
import com.google.cloud.retail.v2.ImportMetadata;
import com.google.cloud.retail.v2.ImportProductsResponse;
import com.google.cloud.retail.v2.ProductInputConfig;
import com.google.cloud.retail.v2.ProductServiceClient;

public class ImportProductCatalog {
    public static void main(String[] args) throws Exception {
        String projectId = "sinuous-anvil-432407-m4";
        String gcsUri = "gs://retailsearch/product.json";

        // Use correct CatalogName constructor with projectId, location, and catalog
        CatalogName parent = CatalogName.of(projectId, "global", "default_catalog");
        GcsSource gcsSource = GcsSource.newBuilder().addInputUris(gcsUri).build();
        ProductInputConfig inputConfig = ProductInputConfig.newBuilder().setGcsSource(gcsSource).build();

        try (ProductServiceClient client = ProductServiceClient.create()) {
            // Ensure correct method call for importProductsAsync
            OperationFuture<ImportProductsResponse, ImportMetadata> responseFuture = client.importProductsAsync(projectId);
            // Await operation completion
            ImportProductsResponse response = responseFuture.get();
            System.out.println("Product import completed: " + response);
        }
    }
}
*/