package cl.schedulator.api.usecases.shared.mappers;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.loader.TaskResponseDto;

public class TaskMapper {

    private Task task;


    private TaskMapper (String taskId, String taskName, Integer duration, Boolean active) {
        this.task =
                Task.builder()
                        .taskId(taskId)
                        .taskName(taskName)
                        .duration(duration)
                        .active(active)
                        .build();
    }


    private TaskMapper (TaskResponseDto response) {
        this.task =
                Task.builder()
                        .taskId(response.getTaskId())
                        .taskName(response.getTaskName())
                        .duration(response.getDuration())
                        .active(true)
                        .build();
    }


    public static TaskMapper create (
            String taskId, String taskName, Integer duration, Boolean active) {
        return new TaskMapper(taskId, taskName, duration, active);
    }


    public static TaskMapper create (TaskResponseDto response) {
        return new TaskMapper(response);
    }


    public Task getTask () {
        return this.task;
    }

}
