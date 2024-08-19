package com.retailsearch.retailsearch;

public enum ProjectConfig {
    RETAIL_PROJECT_ID("sinuous-anvil-432407-m4");

    private final String projectId;

    ProjectConfig(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }
}
