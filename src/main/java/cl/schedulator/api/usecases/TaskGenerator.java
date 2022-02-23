package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.loader.TaskResponseDto;

import java.util.List;

public interface TaskGenerator {
    List<TaskResponseDto> getTaskList();
}
