package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.loader.TaskGateway;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.optimizer.TaskOptimizer;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskGeneratorImpl implements TaskGenerator {

    private TaskGateway gateway;
    private TaskOptimizer optimizer;


    @Autowired
    public TaskGeneratorImpl (TaskGateway gateway, TaskOptimizer optimizer) {
        this.gateway = gateway;
        this.optimizer = optimizer;
    }


    @Override
    public TaskSummary getSummary (TaskSorterEnum orderType) {
        log.info("Getting task from schedule generator microservice");
        List<TaskResponseDto> originTaskList = new ArrayList<>();
        originTaskList = gateway.getTaskList();

        optimizer.transformToEntityTaskList(originTaskList);
        return optimizer.getSummary(orderType);
    }

}
