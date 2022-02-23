package cl.schedulator.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfigurationImp implements WebClientConfiguration {
    private final RoutesConfiguration routesConfiguration;

    @Autowired
    public WebClientConfigurationImp(RoutesConfiguration routesConfiguration) {
        this.routesConfiguration = routesConfiguration;
    }

    @Override
    public WebClient getWebClient() {
        return WebClient
                .builder()
                .baseUrl(this.routesConfiguration.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
