package com.luxoft.panasonic.reportFromJSON.plugin.output;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.UnsortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderForSmallJson;

import java.io.*;

public class JsonResultWriter {
    private String pathToJsonFile;

    public JsonResultWriter(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    public void writeResultsToJson(UnsortedIssue unsortedIssue, String issueStatus, String issuePriority, String domain) {
        try
        {
            OutputStream outputStream = new FileOutputStream(pathToJsonFile);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.setIndent("  ");
            writer.beginArray();
            for (Event event: unsortedIssue.getEventList()) {
                SortedIssue sortedIssue = new SortedIssue(
                        domain, unsortedIssue.getMergeKey(), issuePriority, unsortedIssue.getCheckerName(),
                        unsortedIssue.getStrippedMainEventFilePath(), event.getEventDescription(), event.getLineNumber(), issueStatus);
                // Create a new Gson object
                Gson gson = new Gson();
                //convert the Java object to json
                gson.toJson(sortedIssue, SortedIssue.class, writer);
            }
            writer.endArray();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void compareJsons(JsonReaderForSmallJson bigJson, JsonReaderForSmallJson smallJson) {
    }
}
