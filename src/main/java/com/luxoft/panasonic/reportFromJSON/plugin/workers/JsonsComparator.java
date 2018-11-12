package com.luxoft.panasonic.reportFromJSON.plugin.workers;

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
                    resultIssue = new ResultIssue(sortedIssueInSmallJson.getCid(), sortedIssueInSmallJson.getMergeKey(),
                            sortedIssueInSmallJson.getOccurrences(), sortedIssueInSmallJson.getIssuePriority(), "Not Fixed");
                    resultIssueSet.add(resultIssue);
                    issuePriorityFlag = "Not Fixed";
                    listOfIssueFromBigJson.remove(sortedIssueInBigJson);
                    break;
                }
            }
            if (!issuePriorityFlag.contains("Not Fixed")) {
                resultIssue = new ResultIssue(sortedIssueInSmallJson.getCid(), sortedIssueInSmallJson.getMergeKey(),
                        sortedIssueInSmallJson.getOccurrences(), sortedIssueInSmallJson.getIssuePriority(), "New Detected");
                resultIssueSet.add(resultIssue);
            }
        }

        for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
            resultIssue = new ResultIssue(sortedIssueInBigJson.getCid(), sortedIssueInBigJson.getMergeKey(),
                    sortedIssueInBigJson.getOccurrences(), sortedIssueInBigJson.getIssuePriority(), "Fixed");
            resultIssueSet.add(resultIssue);
        }
    }
}
