package com.whalespottingjava.services;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class PostCodeService {
    private String urlPostCodeApi = "https://api.postcodes.io/postcodes";
    private String resultKeyWord = "result";

    private String postCodeKeyWord = "postcode";

    private String HttpRequestPostCodeApi(String url) throws URISyntaxException {
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

    private JSONObject HandlePostCodeApiResponse(String postCode){
        String url = GetUrlForLonLatRequest(postCode);
        String response = null;
        JSONObject result;
        try {
            response = HttpRequestPostCodeApi(url);
            JSONObject json = new JSONObject(response);
            result = json.getJSONObject(resultKeyWord);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public double GetLatitude(String postCode) {
        JSONObject result = HandlePostCodeApiResponse(postCode);
        return result.getBigDecimal("latitude").doubleValue();
    }
    public double GetLongitude(String postCode) {
        JSONObject result = HandlePostCodeApiResponse(postCode);
        return result.getBigDecimal("longitude").doubleValue();
    }

    public String GetPostCode(double longitude, double latitude) {
        String url = GetUrlForPostCodeRequest(longitude, latitude);
        String response = null;
        JSONObject result;
        String postcode;
        try {
            response = HttpRequestPostCodeApi(url);
            JSONObject json = new JSONObject(response);
            result = json.getJSONArray(resultKeyWord).getJSONObject(0);
            postcode = result.getString(postCodeKeyWord );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return postcode;
    }
    private String GetUrlForPostCodeRequest(double longitude, double latitude) {
        String longitudeInput = "lon=" ;
        String latitudeInput = "lat=";
        return urlPostCodeApi +  "?" + longitudeInput + longitude + "&" + latitudeInput + latitude;
    }
    private String GetUrlForLonLatRequest(String postCode){
        postCode =  postCode.replaceAll("\\s", "");
        return urlPostCodeApi +  "/" + postCode;
    }
}
