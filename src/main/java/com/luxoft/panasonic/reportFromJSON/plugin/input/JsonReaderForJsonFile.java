package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import com.luxoft.panasonic.reportFromJSON.plugin.workers.JsonSorter;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.node.ArrayNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderForJsonFile {
    private String pathToJsonFile;
    private List<SortedIssue> sortedIssueList;

    public JsonReaderForJsonFile(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
        readInputJson();
    }

    public List<SortedIssue> getSortedIssueList() {
        return sortedIssueList;
    }

    private void readInputJson() {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp;
        try {
            jp = f.createJsonParser(new File(pathToJsonFile));

            JsonToken current;

            current = jp.nextToken();
            if (current != JsonToken.START_OBJECT) {
                System.out.println("Error: root should be object: quiting.");
                return;
            }

            while (jp.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jp.getCurrentName();
                // move from field name to field value
                current = jp.nextToken();
                if (fieldName.equals("issueInfo")) {
                    if (current == JsonToken.START_ARRAY) {
                        Integer cid;
                        String mergeKey;
                        String checkerName;
                        String filePath;
                        String domain;

                        sortedIssueList = new ArrayList<>();
                        JsonSorter jsonSorter = new JsonSorter();

                        // For each of the records in the array
                        while (jp.nextToken() != JsonToken.END_ARRAY && jp.getCurrentToken() != JsonToken.END_OBJECT) {
                            JsonNode node = jp.readValueAsTree();
                            if (node.get("cid") == null) {
                                cid = 0;
                            } else {
                                cid = node.get("cid").getIntValue();
                            }
                            if (node.get("mergeKey") == null) {
                                mergeKey = "";
                            } else {
                                mergeKey = node.get("mergeKey").getTextValue();
                            }
                            checkerName = "none";
                            filePath = "none";
                            domain = "none";
                            if (node.get("occurrences").isArray()) {
                                ArrayNode occurrencesArray = (ArrayNode) node.get("occurrences");
                                if (occurrencesArray.size() > 0) {
                                    for (JsonNode arrayNode : occurrencesArray) {
                                        checkerName = arrayNode.get("checker").getTextValue();
                                        filePath = arrayNode.get("file").getTextValue();
                                        domain = arrayNode.get("componentName").getTextValue();
                                    }
                                }
                            }

                            if (jsonSorter.priorityIsHighOrMedium(checkerName)) {
                                SortedIssue sortedIssue = jsonSorter.getSortedIssue(cid, mergeKey, checkerName, filePath, domain);
                                sortedIssueList.add(sortedIssue);
                            }
                        }
                    } else {
                        jp.skipChildren();
                    }
                } else {
                    jp.skipChildren();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
