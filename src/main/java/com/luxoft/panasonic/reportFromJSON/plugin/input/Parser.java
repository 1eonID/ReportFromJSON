package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.luxoft.panasonic.reportFromJSON.plugin.beans.UnsortedIssue;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;

import java.io.File;
import java.util.List;

public class Parser {
    private List<UnsortedIssue> unsortedIssueList;
    private String mergeKey;
    private String checkerName;
    private String strippedMainEventFilePath;
    private String eventDescription;
    private Integer lineNumber;


    public void readFromJSON(String pathToFile) throws Exception {
        JsonFactory f = new MappingJsonFactory();
        JsonParser jp = f.createJsonParser(new File(pathToFile));

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
                    // For each of the records in the array
                    String issuesFieldName = jp.getCurrentName();
                    // move from field name to field value
                    current = jp.nextToken();
                    if (issuesFieldName.equals("mergeKey")) {
                        JsonNode node = jp.readValueAsTree();
                        mergeKey = node.get("mergeKey").getTextValue();
                    }
                    if (issuesFieldName.equals("events")){
                        if (current == JsonToken.START_ARRAY) {
                            while (jp.nextToken() != JsonToken.END_ARRAY) {
                                // read the record into a tree model,
                                // this moves the parsing position to the end of it
                                JsonNode mergeKeyNode = jp.readValueAsTree();
                                strippedMainEventFilePath = mergeKeyNode.get("strippedFilePathname").getTextValue();
                                eventDescription = mergeKeyNode.get("eventDescription").getTextValue();
                                lineNumber = mergeKeyNode.get("lineNumber").getIntValue();
                            }
                        }
                    }
                } else {
                    jp.skipChildren();
                }
            } else {
                jp.skipChildren();
            }
        }
    }
}

// https://www.ngdata.com/parsing-a-large-json-file-efficiently-and-easily/
