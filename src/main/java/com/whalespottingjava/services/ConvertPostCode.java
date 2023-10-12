package com.whalespottingjava.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;


public class ConvertPostCode {

    public String urlPostCodeApi = "https://api.postcodes.io/postcodes/";

    public void RequestLatLongFromPostcode (String postCode) throws URISyntaxException {

        String url = urlPostCodeApi + postCode;
        HttpRequest request = HttpRequest.newBuilder().uri(new URI(url))
                .version(HttpClient.Version.HTTP_2).GET().build();
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            HttpHeaders responseHeaders = response.headers();
            String responseBody = response.body();
            System.out.println(responseBody);
            System.out.println(responseHeaders);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("here");
        //JSONObject json = new JSONObject(responseBody);
    }





}
