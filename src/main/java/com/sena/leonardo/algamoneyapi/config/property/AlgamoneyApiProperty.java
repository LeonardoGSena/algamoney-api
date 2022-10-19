package com.sena.leonardo.algamoneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("algamoney")
public class AlgamoneyApiProperty {

    private String originPermitted = "http:localhost:8000";
    private final Security security = new Security();

    public String getOriginPermitted() {
        return originPermitted;
    }

    public void setOriginPermitted(String originPermitted) {
        this.originPermitted = originPermitted;
    }

    public Security getSecurity() {
        return security;
    }

    public static class Security {
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }

}
