package com.luxoft.panasonic.reportFromJSON.plugin.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class PriorityMap {
    private String checker;
    private String issueCategory;
    private String priority;
}
