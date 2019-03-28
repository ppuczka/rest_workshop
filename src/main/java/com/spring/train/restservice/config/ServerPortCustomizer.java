package com.spring.train.restservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class ServerPortCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Value("${application_1.port")
    private Integer port;

    @Override public void customize(ConfigurableWebServerFactory factory) {
        factory.setPort(port);
    }
}
