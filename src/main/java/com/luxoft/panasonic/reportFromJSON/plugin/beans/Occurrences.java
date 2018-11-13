package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Occurrences {
    private String checkerName;
    private String filePath;
    private String function;
    private Integer mainEventLineNumber;
    private String mainEventDescription;
    private String domain;
    private String issueStatus;
}
