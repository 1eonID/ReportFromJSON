package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SortedIssue {
    private Integer cid;
    private String mergeKey;
    private List<Occurrences> occurrences;
    private String issuePriority;
}
