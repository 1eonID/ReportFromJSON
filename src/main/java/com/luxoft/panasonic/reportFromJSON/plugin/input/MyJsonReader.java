package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.UnsortedIssue;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyJsonReader {
    private String pathToJsonFile;
    private PriorityMapReader priorityMap;
    private ComponentMapReader componentMap;

    public MyJsonReader(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    public void readInputJson() {
        String priority;
        try {
            priorityMap = new PriorityMapReader();
            componentMap = new ComponentMapReader();
            InputStream inputStream = new FileInputStream(pathToJsonFile);
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().create();

            // Read file in stream mode
            reader.beginArray();
            while (reader.hasNext()) {
                // Read data into object model
                UnsortedIssue unsortedIssue = gson.fromJson(reader, UnsortedIssue.class);

            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
