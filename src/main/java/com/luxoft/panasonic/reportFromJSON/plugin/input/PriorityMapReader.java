package com.luxoft.panasonic.reportFromJSON.plugin.input;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.beans.PriorityMap;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class PriorityMapReader {
    private List<PriorityMap> priorityMapList;

    public PriorityMapReader() {
        readFileAndFillMap();
    }

    public List<PriorityMap> getPriorityMapList() {
        return priorityMapList;
    }

    public void setFileRules(List<PriorityMap> priorityMapList) {
        this.priorityMapList = priorityMapList;
    }

    private void readFileAndFillMap() {

        try {
            JsonReader reader = new JsonReader(new InputStreamReader(getClass()
                    .getClassLoader()
                    .getResourceAsStream("priorityMap.json"), "UTF-8"));
            Gson gson = new GsonBuilder().create();
            PriorityMap[] priorityMapsArray = gson.fromJson(reader, PriorityMap[].class);

            for (PriorityMap priorityMap: priorityMapsArray) {
                priorityMapList.add(new PriorityMap(priorityMap.getChecker(), priorityMap.getIssueCategory(), priorityMap.getPriority()));
            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}