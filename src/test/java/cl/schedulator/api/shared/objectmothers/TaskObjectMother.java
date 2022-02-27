package cl.schedulator.api.shared.objectmothers;

import cl.schedulator.api.domain.entities.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskObjectMother {

    private List<Task> tasks;


    private TaskObjectMother () {
        this.tasks = new ArrayList<>();
    }


    public static TaskObjectMother create () {
        return new TaskObjectMother();
    }


    public List<Task> getValidTaskList () {
        this.tasks.add(new Task("t1", "Task 1", 7, true));
        this.tasks.add(new Task("t2", "Task 2", 1, true));
        this.tasks.add(new Task("t3", "Task 3", 8, true));
        return this.tasks;
    }

}
