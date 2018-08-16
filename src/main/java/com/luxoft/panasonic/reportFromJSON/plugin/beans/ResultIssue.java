package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultIssue {
    private String mergeKey;
    private String checkerName;
    private String strippedMainEventFilePath;
    private String eventDescription;
    private Integer lineNumber;
    private String domain;
    private String issuePriority;  //Critical(High) or Major(Medium)
    private String issueStatus;  //NewDetected or Fixed
}