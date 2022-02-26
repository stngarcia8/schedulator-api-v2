package cl.schedulator.api.usecases.optimizer.transformers;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.shared.mappers.TaskMapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskTransform {

    private List<Task> resultTaskList;
    private List<TaskResponseDto> originTaskList;


    private TaskTransform (List<TaskResponseDto> originTaskList) {
        this.originTaskList = originTaskList;
    }


    public static TaskTransform processList (List<TaskResponseDto> originTaskList) {
        return new TaskTransform(originTaskList);
    }


    public List<Task> getTaskList () {
        this.transformList();
        return this.sortResultTaskList();
    }


    private void transformList () {
        this.resultTaskList = new ArrayList<>();
        this.originTaskList.stream()
                .forEach(t -> this.resultTaskList.add(TaskMapper.create(t)
                        .getTask()));
    }


    private List<Task> sortResultTaskList () {
        return this.resultTaskList.stream()
                .sorted(Comparator.comparingInt(Task::getDuration)
                        .reversed())
                .collect(Collectors.toList());
    }

}
