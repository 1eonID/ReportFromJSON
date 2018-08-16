package com.luxoft.panasonic.reportFromJSON.plugin;

import com.luxoft.panasonic.reportFromJSON.plugin.output.JsonResultWriter;
import com.luxoft.panasonic.reportFromJSON.plugin.workers.JsonsComparator;

public class Main {
    public static void main(String[] args) {
        JsonsComparator jsonsComparator = new JsonsComparator();
        jsonsComparator.compare("D:\\report.json", "D:\\ReportFromJSON\\report_small.json");
        JsonResultWriter result = new JsonResultWriter("D:\\ReportFromJson\\output.json");
        result.writeResultsToJson(jsonsComparator.getResultIssueSet());
    }
}
