package com.frejaeid.internship.demo.config;

import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FrejaConfig {
    @Value("${freja.keystore.password}")
    private String keystorePassword;
    @Value("${freja.keystore.path}")
    private String keystorePath;

    @Bean
    public AuthenticationClientApi authenticationClientApi() throws Exception {
        String certPath = new java.io.File(AuthenticationClientApi.class.getClassLoader().getResource("freja_test_root_ca.crt").toURI()).getAbsolutePath();

        SslSettings sslSettings = SslSettings.create(keystorePath, keystorePassword, certPath);

        return AuthenticationClient.create(sslSettings, FrejaEnvironment.TEST).build();
    }
}
