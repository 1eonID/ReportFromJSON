package com.luxoft.panasonic.reportFromJSON.plugin.workers;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.Occurrences;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.ResultIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderForJsonFile;

import java.util.HashSet;
import java.util.List;

public class JsonsComparator {
    private HashSet<ResultIssue> resultIssueSet;

    public HashSet<ResultIssue> getResultIssueSet() {
        return resultIssueSet;
    }

    public void compare(String pathToBigJson, String  pathToSmallJson) {
        JsonReaderForJsonFile jsonReaderFromBigJson = new JsonReaderForJsonFile(pathToBigJson);
        JsonReaderForJsonFile jsonReaderFromSmallJson = new JsonReaderForJsonFile(pathToSmallJson);

        List<SortedIssue> listOfIssueFromBigJson = jsonReaderFromBigJson.getSortedIssueList();
        List<SortedIssue> listOfIssueFromSmallJson = jsonReaderFromSmallJson.getSortedIssueList();

        ResultIssue resultIssue;
        String issuePriorityFlag;
        resultIssueSet = new HashSet<>();

        for (SortedIssue sortedIssueInSmallJson : listOfIssueFromSmallJson) {
            issuePriorityFlag = "";
            for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
                if (sortedIssueInSmallJson.getMergeKey().contains(sortedIssueInBigJson.getMergeKey())) {
                    for (Occurrences occurrences : sortedIssueInSmallJson.getOccurrences()) {
                        occurrences.setIssueStatus("Not Fixed");
                    }
                    resultIssue = new ResultIssue(sortedIssueInSmallJson.getCid(), sortedIssueInSmallJson.getMergeKey(),
                            sortedIssueInSmallJson.getOccurrences(), sortedIssueInSmallJson.getIssuePriority());
                    resultIssueSet.add(resultIssue);
                    listOfIssueFromBigJson.remove(sortedIssueInBigJson);
                    issuePriorityFlag = "Not Fixed";
                    break;
                }
            }

            if (!issuePriorityFlag.contains("Not Fixed")) {
                for (Occurrences occurrences : sortedIssueInSmallJson.getOccurrences()) {
                    occurrences.setIssueStatus("New Detected");
                }
                resultIssue = new ResultIssue(sortedIssueInSmallJson.getCid(), sortedIssueInSmallJson.getMergeKey(),
                        sortedIssueInSmallJson.getOccurrences(), sortedIssueInSmallJson.getIssuePriority());
                resultIssueSet.add(resultIssue);
            }
        }

        for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
            for (Occurrences occurrences : sortedIssueInBigJson.getOccurrences()) {
                occurrences.setIssueStatus("Fixed");
            }
            resultIssue = new ResultIssue(sortedIssueInBigJson.getCid(), sortedIssueInBigJson.getMergeKey(),
                    sortedIssueInBigJson.getOccurrences(), sortedIssueInBigJson.getIssuePriority());
            resultIssueSet.add(resultIssue);
        }
    }
}
