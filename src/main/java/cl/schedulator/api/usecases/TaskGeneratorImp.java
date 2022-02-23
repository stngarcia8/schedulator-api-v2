package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.loader.TaskGateway;
import cl.schedulator.api.usecases.loader.TaskGatewayImp;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TaskGeneratorImp implements TaskGenerator {
    private final TaskGateway taskGateway;

    public TaskGeneratorImp(){}

    @Autowired
    public TaskGeneratorImp(TaskGateway taskGateway) {
        try{
            this.taskGateway = taskGateway;
        } catch ( Exception ex){
            log.info(ex.getMessage());
        }
    }

    @Override
    public List<TaskResponseDto> getTaskList() {
        List<TaskResponseDto> lista = new ArrayList<>();
        return lista;
    }


}
