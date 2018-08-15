package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderForSmallJson {
    private String pathToJsonFile;
    private List<SortedIssue> sortedIssueList;

    public JsonReaderForSmallJson(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    public void readInputJson() {
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
                if (fieldName.equals("issues")) {
                    if (current == JsonToken.START_ARRAY) {
                        String mergeKey;
                        String checkerName;
                        String strippedMainEventFilePath;
                        String subcategory;
                        String eventDescription = "";
                        Integer lineNumber = 0;
                        sortedIssueList = new ArrayList<>();

                        // For each of the records in the array
                        while (jp.nextToken() != JsonToken.END_ARRAY) {
                            JsonNode node = jp.readValueAsTree();
                            mergeKey = node.get("mergeKey").getTextValue();
                            checkerName = node.get("checkerName").getTextValue();
                            subcategory = node.get("subcategory").getTextValue();
                            strippedMainEventFilePath = node.get("strippedMainEventFilePathname").getTextValue();


                            JsonNode eventsNode = node.path("events");
                            if (eventsNode.isArray()) {
                                for (JsonNode evNode: eventsNode) {
                                    eventDescription = evNode.get("eventDescription").getTextValue();
                                    lineNumber = evNode.get("lineNumber").getIntValue();
                                    sortedIssueList.add(new SortedIssue(mergeKey, checkerName, subcategory, strippedMainEventFilePath, eventDescription, lineNumber));
                                }
                            } else {
                                sortedIssueList.add(new SortedIssue(mergeKey, checkerName, subcategory, strippedMainEventFilePath, eventDescription, lineNumber));
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
