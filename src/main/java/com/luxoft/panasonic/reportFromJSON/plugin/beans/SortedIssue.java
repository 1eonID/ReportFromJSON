package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortedIssue {
    private Integer cid;
    private String mergeKey;
    private String checkerName;
    private String filePath;
    private String domain;
    private String issuePriority;
}
