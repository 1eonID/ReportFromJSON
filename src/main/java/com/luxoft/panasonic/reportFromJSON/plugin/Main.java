package com.luxoft.panasonic.reportFromJSON.plugin;

import com.luxoft.panasonic.reportFromJSON.plugin.input.JsonReaderForSmallJson;
import com.luxoft.panasonic.reportFromJSON.plugin.output.JsonResultWriter;

public class Main {
    public static void main(String[] args) {
        JsonReaderForSmallJson bigJson = new JsonReaderForSmallJson(args[0]);
        JsonReaderForSmallJson smallJson = new JsonReaderForSmallJson(args[1]);
        JsonResultWriter result = new JsonResultWriter("D:\\ReportFromJson\\output.json");
        result.compareJsons(bigJson, smallJson);
    }
}
