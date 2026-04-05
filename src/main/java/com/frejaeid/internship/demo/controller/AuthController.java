package com.frejaeid.internship.demo.controller;

import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationClientApi authenticationClient;

    public AuthController(AuthenticationClientApi authenticationClientApi) {
        this.authenticationClient = authenticationClientApi;
    }

    @PostMapping("/initiate")
    public String initiate() throws FrejaEidClientInternalException, FrejaEidException {
        InitiateAuthenticationRequest request = InitiateAuthenticationRequest.createCustom()
                .setEmail("mstankovicdb@gmail.com")
                .build();

        String reference = authenticationClient.initiate(request);

        return reference;
    }

}
