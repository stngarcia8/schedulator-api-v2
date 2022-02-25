package cl.schedulator.api.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class RoutesConfigurationImpl implements RoutesConfiguration {

    private final String baseUrl = "http://localhost:8080";
    private final String taskUri = "/generator/schedule/tasks";

}
