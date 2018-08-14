package com.luxoft.panasonic.reportFromJSON.plugin.beans;

public class PriorityMap {
    private String checker;
    private String issueCategory;
    private String priority;

    public PriorityMap(String checker, String issueCategory, String priority) {
        this.checker = checker;
        this.issueCategory = issueCategory;
        this.priority = priority;
    }

    public String getChecker() {
        return checker;
    }

    public String getIssueCategory() {
        return issueCategory;
    }

    public String getPriority() {
        return priority;
    }
}
