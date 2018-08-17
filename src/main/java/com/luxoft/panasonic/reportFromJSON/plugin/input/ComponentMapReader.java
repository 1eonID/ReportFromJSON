package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.ComponentMap;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class ComponentMapReader {
    private HashMap<String, String> fileRules;

    public ComponentMapReader() {
        readFileAndFillMap();
    }

    public HashMap<String, String> getFileRules() {
        return fileRules;
    }

    private void readFileAndFillMap() {

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getClass()
                    .getClassLoader()
                    .getResourceAsStream("componentMap.json"), StandardCharsets.UTF_8));
            Gson gson = new GsonBuilder().create();
            ComponentMap[] componentMapsArray = gson.fromJson(reader, ComponentMap[].class);
            fileRules = new HashMap<>();

            for (ComponentMap componentMap: componentMapsArray) {
                fileRules.put(componentMap.getPathPattern(), componentMap.getComponentName());
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
