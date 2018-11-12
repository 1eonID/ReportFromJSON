package com.luxoft.panasonic.reportFromJSON.plugin.workers;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.Occurrences;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.PriorityMap;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.PriorityMapReader;

import java.util.List;

public class JsonSorter {
    private final PriorityMapReader priorityMap = new PriorityMapReader();
    private final List<PriorityMap> priorityMapList = priorityMap.getPriorityMapList();
    private String issuePriority;

    private String getIssuePriority() {
        return issuePriority;
    }

    private void setIssuePriority(String issuePriority) {
        this.issuePriority = issuePriority;
    }

    public SortedIssue getSortedIssue(Integer cid, String mergeKey, List<Occurrences> occurrences) {
        issuePriority = getIssuePriority();
        return new SortedIssue(cid, mergeKey, occurrences, issuePriority);
    }

    public boolean priorityIsHighOrMedium(String checkerName) {
        for (PriorityMap priorityMap : priorityMapList) {
            if (checkerName.contains(priorityMap.getChecker())) {
                setIssuePriority(priorityMap.getPriority());
                return true;
            }
        }
        return false;
    }
}
