package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SortedIssue {
    private String mergeKey;
    private String issuePriority;  //Critical(High) or Major(Medium)
    private String checkerName;
    private String domain;
    private String strippedMainEventFilePath;
    private String eventDescription;
    private Integer lineNumber;
    private String issueStatus;  //NewDetected or Fixed

}
