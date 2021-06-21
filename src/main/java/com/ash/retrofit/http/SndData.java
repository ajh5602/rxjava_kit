package com.ash.retrofit.http;

import java.util.HashMap;
import java.util.Map;

public class SndData {
    public Map<String, String> header;
    public String body;
    public SndData() {
        header = new HashMap<String, String>();
        body = new String();
    }
}
