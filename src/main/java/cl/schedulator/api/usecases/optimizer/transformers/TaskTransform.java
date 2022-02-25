package cl.schedulator.api.usecases.optimizer.transformers;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.shared.mappers.TaskMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        this.sortResultTaskList();
        return this.resultTaskList;
    }


    private void transformList () {
        this.resultTaskList = new ArrayList<>();
        this.originTaskList.stream()
                .forEach(t -> this.resultTaskList.add(TaskMapper.create(t)
                        .getTask()));
    }


    private void sortResultTaskList () {
        Collections.sort(this.resultTaskList);
    }

}
