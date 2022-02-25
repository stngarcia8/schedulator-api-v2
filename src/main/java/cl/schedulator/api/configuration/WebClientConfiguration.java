package cl.schedulator.api.configuration;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientConfiguration {

    WebClient getWebClient ();

}
