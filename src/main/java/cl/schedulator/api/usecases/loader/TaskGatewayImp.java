package cl.schedulator.api.usecases.loader;

import cl.schedulator.api.configuration.RoutesConfiguration;
import cl.schedulator.api.configuration.WebClientConfiguration;
import cl.schedulator.api.configuration.WebClientConfigurationImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskGatewayImp implements TaskGateway {

    private final RoutesConfiguration routesConfiguration;
    private final WebClientConfiguration webClientConfiguration;
    private WebClient webClient;
    private List<TaskResponseDto> taskList;

    @Autowired
    public TaskGatewayImp(RoutesConfiguration routesConfiguration, WebClientConfigurationImp webClientConfiguration) {
        this.routesConfiguration = routesConfiguration;
        this.webClientConfiguration = webClientConfiguration;
        this.taskList = new ArrayList<>();
        this.configureWebClient();
        this.loadTasks();
    }

    private void configureWebClient() {
        this.webClient = this.webClientConfiguration.getWebClient();
    }

    private void loadTasks() {
        Flux<TaskResponseDto> taskFlux = webClient
                .get()
                .uri(this.routesConfiguration.getTaskUri())
                .retrieve()
                .bodyToFlux(TaskResponseDto.class);
        this.taskList = taskFlux
                .collectList()
                .block();
    }

    @Override
    public List<TaskResponseDto> getTaskList() {
        return this.taskList;
    }


}
