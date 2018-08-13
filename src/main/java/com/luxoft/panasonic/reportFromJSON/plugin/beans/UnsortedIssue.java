package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnsortedIssue {
    private String mergeKey;
    private String checkerName;
    private String strippedMainEventFilePath;
    private String eventDescription;
    private Integer lineNumber;

}
