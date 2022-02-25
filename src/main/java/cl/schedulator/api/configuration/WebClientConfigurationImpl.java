package cl.schedulator.api.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfigurationImpl implements WebClientConfiguration {

    RoutesConfiguration routes;


    public WebClientConfigurationImpl (RoutesConfiguration routes) {
        this.routes = routes;
    }


    @Override
    public WebClient getWebClient () {
        return WebClient.builder()
                .baseUrl(this.routes.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}
