package com.guitarshack;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpSalesRepository implements SalesRepository {

    @Override
    public SalesTotal getSalesTotal(int productId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Calendar.getInstance().getTime());
        Date endDate = calendar.getTime();
        calendar.add(Calendar.DATE, -30);
        Date startDate = calendar.getTime();
        DateFormat format = new SimpleDateFormat("M/d/yyyy");
        Map<String, Object> params1 = new HashMap<>() {{
            put("productId", productId);
            put("startDate", format.format(startDate));
            put("endDate", format.format(endDate));
            put("action", "total");
        }};
        String paramString1 = "?";

        for (String key : params1.keySet()) {
            paramString1 += key + "=" + params1.get(key).toString() + "&";
        }

        HttpRequest request1 = HttpRequest
                .newBuilder(URI.create("https://gjtvhjg8e9.execute-api.us-east-2.amazonaws.com/default/sales" + paramString1))
                .build();
        String result1 = "";
        HttpClient httpClient1 = HttpClient.newHttpClient();
        HttpResponse<String> response1 = null;
        try {
            response1 = httpClient1.send(request1, HttpResponse.BodyHandlers.ofString());
            result1 = response1.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(result1, SalesTotal.class);
    }
}
