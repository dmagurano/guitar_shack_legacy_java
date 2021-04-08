package com.guitarshack;

import com.google.gson.Gson;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class HttpProductRepository implements ProductRepository {

    private WebClient webClient;

    public HttpProductRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Product getProduct(int productId) {
        String baseURL = "https://6hr1390c1j.execute-api.us-east-2.amazonaws.com/default/product";
        Map<String, Object> params = new HashMap<>() {{
            put("id", productId);
        }};
        String paramString = "?";

        for (String key : params.keySet()) {
            paramString += key + "=" + params.get(key).toString() + "&";
        }
        URI uri = URI.create(baseURL + paramString);

        String result = webClient.getResponse(uri);

        return new Gson().fromJson(result, Product.class);
    }

}
