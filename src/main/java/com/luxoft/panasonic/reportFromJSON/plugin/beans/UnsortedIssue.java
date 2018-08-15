package com.luxoft.panasonic.reportFromJSON.plugin.beans;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnsortedIssue {
    private String mergeKey;
    private String checkerName;
    private String subcategory;
    private String strippedMainEventFilePath;
    private String eventDescription;
    private Integer lineNumber;

}
