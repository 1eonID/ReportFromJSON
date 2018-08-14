package com.luxoft.panasonic.reportFromJSON.plugin;

import com.luxoft.panasonic.reportFromJSON.plugin.input.MyJsonReader;
import com.luxoft.panasonic.reportFromJSON.plugin.output.MyJsonWriter;

public class Main {
    public static void main(String[] args) {
        MyJsonReader bigJson = new MyJsonReader(args[0]);
        MyJsonReader smallJson = new MyJsonReader(args[1]);
        MyJsonWriter result = new MyJsonWriter(args[2]);
        result.compareJsons(bigJson, smallJson);
    }
}
