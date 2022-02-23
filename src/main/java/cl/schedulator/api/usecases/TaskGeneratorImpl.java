package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.loader.TaskGateway;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskGeneratorImpl implements TaskGenerator {
    private TaskGateway gateway;

    @Autowired
    public TaskGeneratorImpl(TaskGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<TaskResponseDto> getTaskList() {
        log.info("Getting task from schedule generator microservice");
        return gateway.getTaskList();
    }


}
