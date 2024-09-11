package com.retailsearch.retailsearch.config;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//controller->service->repository->entity(Config is for setting and imported to import external  data)
//getService(): This method retrieves the Storage service based on the configuration provided
// by the StorageOptions.
// It is used to create an instance of the Storage client, which allows the code to interact with GCS.
     public class GcsConfig {
         @Bean
         public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }
}