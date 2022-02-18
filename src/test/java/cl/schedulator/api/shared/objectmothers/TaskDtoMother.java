package cl.schedulator.api.shared.objectmothers;

import cl.schedulator.api.domain.dtos.TaskDto;

public class TaskDtoMother {
    private TaskDto taskDto;

    private TaskDtoMother(String taskId, String taskName, Integer duration) {
        this.taskDto = TaskDto.builder()
                .taskId(taskId)
                .taskName(taskName)
                .duration(duration)
                .build();
    }

    public static TaskDtoMother create() {
        return new TaskDtoMother("1", "Task name", 5);
    }

    public TaskDto getValidTask(){
        return this.taskDto;
    }

}
