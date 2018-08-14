package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.ComponentMap;

import java.io.*;
import java.util.HashMap;

public class ComponentMapReader {
    private HashMap<String, String> fileRules;

    public ComponentMapReader() {
        readFileAndFillMap();
    }

    public HashMap<String, String> getFileRules() {
        return fileRules;
    }

    public void setFileRules(HashMap<String, String> fileRules) {
        this.fileRules = fileRules;
    }

    private void readFileAndFillMap() {

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getClass()
                    .getClassLoader()
                    .getResourceAsStream("componentMap.json"), "UTF-8"));
            Gson gson = new GsonBuilder().create();
            ComponentMap[] componentMapsArray = gson.fromJson(reader, ComponentMap[].class);

            for (ComponentMap componentMap: componentMapsArray) {
                fileRules.put(componentMap.getComponentName(), componentMap.getPathPattern());
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
