package com.whalespottingjava.services;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class PostCodeService {
    private String URL_POST_CODE_API = "https://api.postcodes.io/postcodes";
    private String resultKeyWord = "result";
    private String postCodeKeyWord = "postcode";
    private String httpRequestPostCodeApi(String url) throws URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .version(HttpClient.Version.HTTP_2).GET().build();
        String responseBody;
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }

        return responseBody;
    }
    private JSONObject handlePostCodeApiResponse(String postCode){
        String url = getUrlForLonLatRequest(postCode);
        String response = null;
        JSONObject result;
        try {
            response = httpRequestPostCodeApi(url);
            JSONObject json = new JSONObject(response);
            result = json.getJSONObject(resultKeyWord);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public double getLatitude(String postCode) {
        JSONObject result = handlePostCodeApiResponse(postCode);
        return result.getBigDecimal("latitude").doubleValue();
    }
    public double getLongitude(String postCode) {
        JSONObject result = handlePostCodeApiResponse(postCode);
        return result.getBigDecimal("longitude").doubleValue();
    }
    public String getPostCode(double longitude, double latitude) {
        String url = getUrlForPostCodeRequest(longitude, latitude);
        String response = null;
        JSONObject result;
        String postcode;
        try {
            response = httpRequestPostCodeApi(url);
            JSONObject json = new JSONObject(response);
            result = json.getJSONArray(resultKeyWord).getJSONObject(0);
            postcode = result.getString(postCodeKeyWord );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return postcode;
    }
    private String getUrlForPostCodeRequest(double longitude, double latitude) {
        String longitudeInput = "lon=" ;
        String latitudeInput = "lat=";
        return urlPostCodeApi +  "?" + longitudeInput + longitude + "&" + latitudeInput + latitude;
    }
    private String getUrlForLonLatRequest(String postCode){
        postCode =  postCode.replaceAll("\\s", "");
        return urlPostCodeApi +  "/" + postCode;
    }
}
