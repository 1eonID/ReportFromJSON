package com.luxoft.panasonic.reportFromJSON.plugin;

import com.luxoft.panasonic.reportFromJSON.plugin.output.JsonResultWriter;
import com.luxoft.panasonic.reportFromJSON.plugin.workers.JsonsComparator;

public class Main {
    public static void main(String[] args) {
        JsonsComparator jsonsComparator = new JsonsComparator();
        jsonsComparator.compare(args[0], args[1]);
        JsonResultWriter result = new JsonResultWriter(args[2]);
        result.writeResultsToJson(jsonsComparator.getResultIssueSet());
    }
}
