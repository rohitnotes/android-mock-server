package com.xpf.android.mock.mock;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:
 */
public class MockRequest {

    public static Map<String, String> getQuery(Request request) {
        Map<String, String> query = new HashMap<>();
        String url = request.url().toString();
        if (url.contains("?")) {
            String[] data = url.split("\\?");
            if (data[1] != null) {
                String[] params = data[1].split("&");
                for (String param : params) {
                    String[] s = param.split("=");
                    query.put(s[0], s[1]);
                }
            }
        }

        return query;
    }
}
