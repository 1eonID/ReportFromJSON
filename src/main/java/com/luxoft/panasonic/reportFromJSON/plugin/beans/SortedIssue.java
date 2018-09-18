package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortedIssue {
    private String mergeKey;
    private Integer occurrenceNumberInMK;
    private String checkerName;
    private String strippedMainEventFilePath;
    private Integer mainEventLineNumber;
    private String functionMangledName;
    private String domain;
    private String issuePriority;  //Critical(High) or Major(Medium)
}
