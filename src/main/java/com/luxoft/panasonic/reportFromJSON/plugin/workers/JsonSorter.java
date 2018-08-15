package com.luxoft.panasonic.reportFromJSON.plugin.workers;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.PriorityMap;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.ComponentMapReader;
import com.luxoft.panasonic.reportFromJSON.plugin.input.PriorityMapReader;

import java.util.HashMap;
import java.util.List;

public class JsonSorter {
    private final PriorityMapReader priorityMap = new PriorityMapReader();
    private final ComponentMapReader componentMap = new ComponentMapReader();
    private final List<PriorityMap> priorityMapList = priorityMap.getPriorityMapList();
    private final HashMap<String, String> componentHashMap = componentMap.getFileRules();
    private final String eventDescription = "";
    private final Integer lineNumber = 0;

    public SortedIssue getSortedIssue(String mergeKey, String checkerName, String strippedMainEventFilePath, String subcategory) {
        String domain = "";
        String issuePriority;

        for (PriorityMap priorityMap : priorityMapList) {
            if (checkerName.contains(priorityMap.getChecker()) && subcategory.contains(priorityMap.getIssueCategory())) {
                issuePriority = priorityMap.getPriority();
                for (HashMap.Entry<String, String> entry : componentHashMap.entrySet()) {
                    if (strippedMainEventFilePath.contains(entry.getKey())) {
                        domain = entry.getValue();
                    }
                }
                return new SortedIssue(mergeKey, checkerName, strippedMainEventFilePath, eventDescription, lineNumber, domain, issuePriority);
            }
        }
    }
}
