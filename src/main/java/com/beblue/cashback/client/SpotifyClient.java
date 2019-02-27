package com.beblue.cashback.client;

import com.beblue.cashback.client.dto.TokenResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class SpotifyClient {

    @Value("${spotify.client.id}")
    private String clientId;

    @Value("${spotify.client.secret}")
    private String clientSecret;

    @Value("${spotify.url}")
    private String url;

    @Value("${spotify.token.url}")
    private String tokenUrl;

    public TokenResponse getToken(){
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = tokenUrl;
        HttpEntity<MultiValueMap> request = new HttpEntity<MultiValueMap>(getAuthBody(),getAuthHeaders());
        ResponseEntity<TokenResponse> response = restTemplate.exchange(url, HttpMethod.POST, request,  TokenResponse.class);

        return response.getBody();
    }

    private MultiValueMap getAuthBody(){
        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
        parametersMap.add("grant_type", "client_credentials");
        return parametersMap;
    }

    private HttpHeaders getAuthHeaders(){
        String plainCreds = clientId.concat(":").concat(clientSecret);
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.add("Content-Type","application/x-www-form-urlencoded");

        return headers;
    }
}
