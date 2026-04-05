package com.frejaeid.internship.demo.controller;

import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    private final AuthenticationClientApi authenticationClient;

    public AuthController(AuthenticationClientApi authenticationClientApi) {
        this.authenticationClient = authenticationClientApi;
    }

    @PostMapping("/initiate")
    public Map<String, String> initiate() throws FrejaEidClientInternalException, FrejaEidException, IOException, InterruptedException {
        InitiateAuthenticationRequest request = InitiateAuthenticationRequest.createCustom().setInferred().build();
//                .setEmail("mstankovicdb@gmail.com")
//                .build();

        String reference = authenticationClient.initiate(request);
        String frejaLink = "https://app.test.frejaeid.com/freja?action=bindUserToTransaction&transactionReference=" + reference;
        String encodedLink = URLEncoder.encode(frejaLink, StandardCharsets.UTF_8);
        String qrUrl = "https://resources.test.frejaeid.com/qrcode/generate?qrcodedata=" + encodedLink;

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(URI.create(qrUrl))
                        .GET()
                        .build();

        HttpResponse<byte[]> qrResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
        byte[] qrBytes = qrResponse.body();
        String qrBase64 = Base64.getEncoder().encodeToString(qrBytes);

        Map<String, String> response = new HashMap<>();
        response.put("reference", reference);
        response.put("qrCode", qrBase64);

        return response;
    }

}
