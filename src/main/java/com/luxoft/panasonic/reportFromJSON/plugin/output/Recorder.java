package com.luxoft.panasonic.reportFromJSON.plugin.output;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class Recorder {
    private JSONOrderedObject recordingObj;
    private JSONOrderedObject domainObj;
    private JSONOrderedObject keyObj;

    public void recordResultsIntoFile(String pathToFile, List<SortedIssue> issueList) {

        recordingObj = new JSONOrderedObject();

        for (SortedIssue issue : issueList) {
            domainObj = new JSONOrderedObject();
            keyObj = new JSONOrderedObject();

            keyObj.put("issueStatus", issue.getIssueStatus());
            keyObj.put("eventDescription", issue.getEventDescription());
            keyObj.put("filePathName", issue.getStrippedMainEventFilePath());
            keyObj.put("checker", issue.getCheckerName());
            keyObj.put("lineNumber", issue.getLineNumber());
            keyObj.put("issuePriority", issue.getIssuePriority());

            domainObj.put("mergeKey", issue.getMergeKey());
            recordingObj.put("Domain", issue.getDomain());
        }

        String jsonFile = new PrintResultInJsonFormat().convertToPrettyJSONUtility(recordingObj);
        try {
            Writer writer = new FileWriter(pathToFile);
            writer.write(jsonFile);
            writer.flush();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }
}
