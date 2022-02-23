package cl.schedulator.api.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component
public class RoutesConfigurationImp implements RoutesConfiguration {

    private String baseUrl = "http://localhost:8080";
    private String taskUri = "/generator/schedule/tasks";

}
