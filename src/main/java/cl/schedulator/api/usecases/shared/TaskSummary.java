package cl.schedulator.api.usecases.shared;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TaskSummary {

    private Integer totalTasks;
    private Integer totalDays;

    @Setter
    private List<DailyTask> days;


    public TaskSummary (Integer totalTasks) {
        this.totalTasks = totalTasks;
        this.totalDays = 0;
        this.days = new ArrayList<>();
    }


    public void addDay (DailyTask day) {
        this.days.add(day);
        this.totalDays = this.days.size();
    }


    public Boolean foundTasks () {
        return totalTasks == 0 ? false : true;
    }

}
