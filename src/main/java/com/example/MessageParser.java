package com.example;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageParser {
    private String url;

    public MessageParser(String url) {
        this.url = url;
    }

    public Map<String, String> ParseMe(String url) {
        try {
            URL u = new URL(url);
            Map<String, String> map = splitQuery(u);
            System.out.println(map.get("lat"));
            System.out.println(map.get("long"));
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Black magic ftw
    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
