package com.luxoft.panasonic.reportFromJSON.plugin.workers;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.ResultIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderForSmallJsonFile;
import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderForSortedBigJson;

import java.util.HashSet;
import java.util.List;

public class JsonsComparator {
    private HashSet<ResultIssue> resultIssueSet;

    public HashSet<ResultIssue> getResultIssueSet() {
        return resultIssueSet;
    }

    public void compare(String pathToBigJson, String  pathToSmallJson) {
        JsonReaderForSortedBigJson jsonReaderFromBigJson = new JsonReaderForSortedBigJson(pathToBigJson);
        JsonReaderForSmallJsonFile jsonReaderFromSmallJson = new JsonReaderForSmallJsonFile(pathToSmallJson);

        List<SortedIssue> listOfIssueFromBigJson = jsonReaderFromBigJson.getSortedIssueList();
        List<SortedIssue> listOfIssueFromSmallJson = jsonReaderFromSmallJson.getSortedIssueList();

        ResultIssue resultIssue;
        String issuePriorityFlag;
        resultIssueSet = new HashSet<>();

        for (SortedIssue sortedIssueInSmallJson : listOfIssueFromSmallJson) {
            if (sortedIssueInSmallJson.getFunctionMangledName() == null) {
                sortedIssueInSmallJson.setFunctionMangledName("");
            }
            issuePriorityFlag = "";
            for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
                if (sortedIssueInBigJson.getFunctionMangledName() == null) {
                    sortedIssueInBigJson.setFunctionMangledName("");
                }
                if (sortedIssueInSmallJson.getMergeKey().contains(sortedIssueInBigJson.getMergeKey()) &&
                        sortedIssueInSmallJson.getMainEventLineNumber().equals(sortedIssueInBigJson.getMainEventLineNumber()) &&
                            sortedIssueInSmallJson.getOccurrenceNumberInMK().equals(sortedIssueInBigJson.getOccurrenceNumberInMK()) &&
                                sortedIssueInSmallJson.getFunctionMangledName().equals(sortedIssueInBigJson.getFunctionMangledName())) {
                    resultIssue = new ResultIssue(sortedIssueInSmallJson.getMergeKey(), sortedIssueInSmallJson.getOccurrenceNumberInMK(),
                            sortedIssueInSmallJson.getCheckerName(), sortedIssueInSmallJson.getStrippedMainEventFilePath(),
                            sortedIssueInSmallJson.getMainEventLineNumber(), sortedIssueInSmallJson.getFunctionMangledName(), sortedIssueInSmallJson.getDomain(),
                            sortedIssueInSmallJson.getIssuePriority(), "Not Fixed");
                    resultIssueSet.add(resultIssue);
                    issuePriorityFlag = "Not Fixed";
                    listOfIssueFromBigJson.remove(sortedIssueInBigJson);
                    break;
                }
            }
            if (!issuePriorityFlag.contains("Not Fixed")) {
                resultIssue = new ResultIssue(sortedIssueInSmallJson.getMergeKey(), sortedIssueInSmallJson.getOccurrenceNumberInMK(),
                        sortedIssueInSmallJson.getCheckerName(), sortedIssueInSmallJson.getStrippedMainEventFilePath(),
                        sortedIssueInSmallJson.getMainEventLineNumber(), sortedIssueInSmallJson.getFunctionMangledName(), sortedIssueInSmallJson.getDomain(),
                        sortedIssueInSmallJson.getIssuePriority(), "New Detected");
                resultIssueSet.add(resultIssue);
            }
        }

        for (SortedIssue sortedIssueInBigJson : listOfIssueFromBigJson) {
            if (sortedIssueInBigJson.getFunctionMangledName() == null) {
                sortedIssueInBigJson.setFunctionMangledName("");
            }
            resultIssue = new ResultIssue(sortedIssueInBigJson.getMergeKey(), sortedIssueInBigJson.getOccurrenceNumberInMK(),
                    sortedIssueInBigJson.getCheckerName(), sortedIssueInBigJson.getStrippedMainEventFilePath(),
                    sortedIssueInBigJson.getMainEventLineNumber(), sortedIssueInBigJson.getFunctionMangledName(), sortedIssueInBigJson.getDomain(),
                    sortedIssueInBigJson.getIssuePriority(), "Fixed");
            resultIssueSet.add(resultIssue);
        }
    }
}
