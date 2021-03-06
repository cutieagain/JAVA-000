package com.cutie.geek.java0.week2;

import okhttp3.*;

import java.io.IOException;

public class OkHttpRequest {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final String URL = "http://localhost:8088/api/hello";

    public static void main(String[] args) {
        try {
            String result = new OkHttpRequest().get(URL);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    OkHttpClient client = new OkHttpClient();
    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
