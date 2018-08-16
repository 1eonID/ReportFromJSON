package com.luxoft.panasonic.reportFromJSON.plugin.workers;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.ResultIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderFromFile;

import java.util.HashSet;
import java.util.List;

public class JsonsComparator {
    private HashSet<ResultIssue> resultIssueSet;

    public HashSet<ResultIssue> getResultIssueSet() {
        return resultIssueSet;
    }

    public void compare(String pathToBigJson, String  pathToSmallJson) {
        JsonReaderFromFile jsonReaderFromBigJson = new JsonReaderFromFile(pathToBigJson);
        JsonReaderFromFile jsonReaderFromSmallJson = new JsonReaderFromFile(pathToSmallJson);

        List<SortedIssue> listOfIssueFromBigJson = jsonReaderFromBigJson.getSortedIssueList();
        List<SortedIssue> listOfIssueFromSmallJson = jsonReaderFromSmallJson.getSortedIssueList();

        ResultIssue resultIssue;
        resultIssueSet = new HashSet<>();

        for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
            for (SortedIssue sortedIssueInSmallJson : listOfIssueFromSmallJson) {
                if (sortedIssueInSmallJson.getMergeKey().contains(sortedIssueInBigJson.getMergeKey()) &&
                    sortedIssueInSmallJson.getEventDescription().contains(sortedIssueInBigJson.getEventDescription()) &&
                    sortedIssueInSmallJson.getLineNumber() == sortedIssueInBigJson.getLineNumber()) {
                    resultIssue = new ResultIssue(sortedIssueInSmallJson.getMergeKey(),
                            sortedIssueInSmallJson.getCheckerName(), sortedIssueInSmallJson.getStrippedMainEventFilePath(),
                            sortedIssueInSmallJson.getEventDescription(), sortedIssueInSmallJson.getLineNumber(),
                            sortedIssueInSmallJson.getDomain(), sortedIssueInSmallJson.getIssuePriority(), "Not Fixed");
                    resultIssueSet.add(resultIssue);
                } else {
                    resultIssue = new ResultIssue(sortedIssueInSmallJson.getMergeKey(),
                            sortedIssueInSmallJson.getCheckerName(), sortedIssueInSmallJson.getStrippedMainEventFilePath(),
                            sortedIssueInSmallJson.getEventDescription(), sortedIssueInSmallJson.getLineNumber(),
                            sortedIssueInSmallJson.getDomain(), sortedIssueInSmallJson.getIssuePriority(), "New Detected");
                    resultIssueSet.add(resultIssue);
                }
            }
        }

        for (SortedIssue sortedIssueInSmallJson : listOfIssueFromSmallJson) {
            for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
                if (sortedIssueInBigJson.getMergeKey().contains(sortedIssueInSmallJson.getMergeKey()) &&
                        sortedIssueInBigJson.getEventDescription().contains(sortedIssueInSmallJson.getEventDescription()) &&
                        sortedIssueInBigJson.getLineNumber() == sortedIssueInSmallJson.getLineNumber()) {
                } else {
                    resultIssue = new ResultIssue(sortedIssueInBigJson.getMergeKey(),
                            sortedIssueInBigJson.getCheckerName(), sortedIssueInBigJson.getStrippedMainEventFilePath(),
                            sortedIssueInBigJson.getEventDescription(), sortedIssueInBigJson.getLineNumber(),
                            sortedIssueInBigJson.getDomain(), sortedIssueInBigJson.getIssuePriority(), "Fixed");
                    resultIssueSet.add(resultIssue);
                }
            }
        }
    }
}
