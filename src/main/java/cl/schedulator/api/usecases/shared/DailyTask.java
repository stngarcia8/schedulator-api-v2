package cl.schedulator.api.usecases.shared;

import cl.schedulator.api.domain.entities.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class DailyTask implements Comparable<DailyTask> {

    @Setter
    private Integer dayNumber;

    private Integer taskPerDay;
    private List<Task> tasks;


    public DailyTask (Integer dayNumber) {
        this.dayNumber = dayNumber;
        this.tasks = new ArrayList<>();
        this.taskPerDay = 0;
    }


    @Override
    public int compareTo (DailyTask comparableDailyTask) {
        if (comparableDailyTask == null || this.taskPerDay == null) return 0;
        if (this.taskPerDay < comparableDailyTask.taskPerDay) return 1;
        if (this.taskPerDay > comparableDailyTask.getTaskPerDay()) return -1;
        return 0;
    }


    public void addTask (Task task) {
        this.tasks.add(task);
        this.taskPerDay = this.tasks.size();
    }

}
