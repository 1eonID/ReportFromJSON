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
    private String issuePriority;

    private String getIssuePriority() {
        return issuePriority;
    }

    private void setIssuePriority(String issuePriority) {
        this.issuePriority = issuePriority;
    }


    public SortedIssue getSortedIssue(String mergeKey, Integer occurrenceNumberInMK, String checkerName, String strippedMainEventFilePath, Integer mainEventLineNumber, String  functionMangledName) {
        String domain = "Assign to domain";
        issuePriority = getIssuePriority();

        for (HashMap.Entry<String, String> entry : componentHashMap.entrySet()) {
            if (strippedMainEventFilePath.matches(entry.getKey())) {
                domain = entry.getValue();
            }
        }
        return new SortedIssue(mergeKey, occurrenceNumberInMK, checkerName, strippedMainEventFilePath, mainEventLineNumber, functionMangledName, domain, issuePriority);
    }

    public boolean priorityIsHighOrMedium(String checkerName) {
        for (PriorityMap priorityMap : priorityMapList) {
            if (checkerName.contains(priorityMap.getChecker())) { //Only Checker, without Subcategory
                setIssuePriority(priorityMap.getPriority());
                return true;
            }
        }
        return false;
    }
}
