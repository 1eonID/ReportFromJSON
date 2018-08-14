package com.luxoft.panasonic.reportFromJSON.plugin.beans;


import lombok.Data;

import java.util.List;

@Data
public class UnsortedIssue {
    private String mergeKey;
    private String checkerName;
    private String subcategory;
    private String strippedMainEventFilePath;
    private List<Event> eventList;

}
