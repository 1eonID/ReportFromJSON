package com.luxoft.panasonic.reportFromJSON.plugin.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.ResultIssue;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;

public class JsonResultWriter {
    private String pathToJsonFile;

    public JsonResultWriter(String pathToJsonFile) {
        this.pathToJsonFile = pathToJsonFile;
    }

    public void writeResultsToJson(HashSet<ResultIssue> resultIssueSet) {
        try
        {
            OutputStream outputStream = new FileOutputStream(pathToJsonFile);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            writer.beginArray();

            Iterator iterator = resultIssueSet.iterator();
            while (iterator.hasNext()) {
                // Create a new Gson object
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
                //convert the Java object to json
                writer.setIndent("\t");
                gson.toJson(iterator.next(), ResultIssue.class, writer);
            }

            writer.endArray();
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
