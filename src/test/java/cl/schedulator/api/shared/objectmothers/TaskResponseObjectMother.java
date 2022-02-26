package cl.schedulator.api.shared.objectmothers;

import cl.schedulator.api.usecases.loader.TaskResponseDto;

import java.util.ArrayList;
import java.util.List;

public class TaskResponseObjectMother {

    private List<TaskResponseDto> tasks;


    private TaskResponseObjectMother () {
        this.tasks = new ArrayList<>();
        this.tasks.add(new TaskResponseDto("t1", "Task 1", 7));
        this.tasks.add(new TaskResponseDto("t2", "Task 2", 1));
        this.tasks.add(new TaskResponseDto("t3", "Task 3", 8));
    }


    public static TaskResponseObjectMother createMother () {
        return new TaskResponseObjectMother();
    }


    public List<TaskResponseDto> getTask () {
        return this.tasks;
    }


    public TaskResponseDto getFirstElement () {
        return this.tasks.get(0);
    }


    public TaskResponseDto getLastElement () {
        return this.tasks.get(this.tasks.size() / 1);
    }


}
