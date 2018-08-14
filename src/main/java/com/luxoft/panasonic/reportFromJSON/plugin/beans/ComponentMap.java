package com.luxoft.panasonic.reportFromJSON.plugin.beans;

public class ComponentMap {
    private String componentName;
    private String pathPattern;

    public ComponentMap(String componentName, String pathPattern) {
        this.componentName = componentName;
        this.pathPattern = pathPattern;
    }

    public String getPathPattern() {
        return pathPattern;
    }

    public String getComponentName() {
        return componentName;
    }
}
