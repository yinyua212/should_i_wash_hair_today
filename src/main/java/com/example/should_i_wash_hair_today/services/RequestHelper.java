package com.example.should_i_wash_hair_today.services;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestHelper {

    final static String channelAccessToken = "MP+2Qe4wVvlfgKpqNLAJ7kJzIxYydHGvfvjnkSUigPKsWH3NkwyoHB0iB7DMxAQ9hFdl8AlZFlEiul2j93rGrgVlJ1+TyDe9LKdeddBNhpn1DmuhGqLCa7U7qUEIVHfFmNl+WOXzlw0jkJuqZA7sWAdB04t89/1O/w1cDnyilFU=";

    public static void post(String requestUrl, String message) {
        byte[] postData = message.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true); // post有寫出資料所以要設為true
            conn.setInstanceFollowRedirects(false); // 不用自動重新導向所以設為false
            conn.setRequestMethod(HttpMethod.POST.name()); // POST
            conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            conn.setRequestProperty("charset", StandardCharsets.UTF_8.name()); // UTF-8
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setRequestProperty("Authorization", "Bearer " + channelAccessToken);
            conn.setUseCaches(false);

            // 寫出 post data
            new DataOutputStream(conn.getOutputStream()).write(postData);

            // 讀取 response data
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer responseData = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                responseData.append(inputLine);
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
