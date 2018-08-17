package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.SortedIssue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderForSortedBigJson {
    private String pathToJsonFile;
    private List<SortedIssue> sortedIssueList;

    public JsonReaderForSortedBigJson(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
        readInputJson();
    }

    public List<SortedIssue> getSortedIssueList() {
        return sortedIssueList;
    }

    private void readInputJson() {
        try {
            sortedIssueList = new ArrayList<>();
            InputStream inputStream = new FileInputStream(pathToJsonFile);
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().create();
            SortedIssue[] sortedIssues = gson.fromJson(reader, SortedIssue[].class);

            for (SortedIssue sortedIssue: sortedIssues) {
                sortedIssueList.add(sortedIssue);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
