package com.luxoft.panasonic.reportFromJSON.plugin.output;

import org.codehaus.jackson.map.ObjectMapper;
import java.io.IOException;

class PrintResultInJsonFormat {

    public String convertToPrettyJSONUtility(JSONOrderedObject simpleJSON) {

        String jsonPrettyStr = simpleJSON.toJSONString();

        try {
            ObjectMapper mapperObj = new ObjectMapper();
            jsonPrettyStr = mapperObj.writerWithDefaultPrettyPrinter().writeValueAsString(simpleJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonPrettyStr;
    }
}
