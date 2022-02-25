package cl.schedulator.api.usecases.loader;

import cl.schedulator.api.configuration.RoutesConfiguration;
import cl.schedulator.api.configuration.WebClientConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TaskGatewayImpl implements TaskGateway {

    final RoutesConfiguration routes;
    final WebClientConfiguration webClientConfig;
    WebClient webClient;
    private List<TaskResponseDto> taskList;


    public TaskGatewayImpl (RoutesConfiguration routes, WebClientConfiguration webClientConfig) {
        this.routes = routes;
        this.webClientConfig = webClientConfig;
        this.taskList = new ArrayList<>();
    }


    private void configureWebClient () {
        this.webClient = this.webClientConfig.getWebClient();
    }


    private void loadTasks () {
        log.info("Loading task from microservice");
        try {
            Flux<TaskResponseDto> taskFlux =
                    webClient
                            .get()
                            .uri(this.routes.getTaskUri())
                            .retrieve()
                            .bodyToFlux(TaskResponseDto.class);
            this.taskList = taskFlux.collectList()
                    .block();
            log.info("Task loaded from microservice, " + this.taskList.size() + " task loaded");
        } catch (Exception ex) {
            log.error("Error while try to load task from microservice " + ex.getMessage());
            this.taskList = new ArrayList<>();
        }
    }


    @Override
    public List<TaskResponseDto> getTaskList () {
        this.configureWebClient();
        this.loadTasks();
        return this.taskList;
    }

}
