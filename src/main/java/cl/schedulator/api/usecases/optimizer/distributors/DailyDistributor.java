package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.shared.DailyTask;

public class DailyDistributor {

    private final Integer MAX_DURATION = 8;
    private DailyTask day;
    private Integer actualDuration;
    private TaskChecker checker;


    private DailyDistributor (Integer inProgressDay, TaskChecker checker) {
        this.checker = checker;
        this.day = new DailyTask(inProgressDay);
        this.actualDuration = 0;
    }


    public static DailyDistributor processDay (Integer inProgressDay, TaskChecker checker) {
        return new DailyDistributor(inProgressDay, checker);
    }


    public void addTaskToDay (Task task) {
        Task newTask = task;
        Integer possibleDuration;
        Integer durationDiference;
        do {
            possibleDuration = this.actualDuration + newTask.getDuration();
            if (possibleDuration <= this.MAX_DURATION && checker.canProcessThisTask(newTask)) {
                this.day.addTask(newTask);
                checker.checkIt(newTask);
                this.actualDuration = possibleDuration;
            }
            durationDiference = this.MAX_DURATION - this.actualDuration;
            if (durationDiference == 0) break;
            TaskSearcher searcher = TaskSearcher.prepareSearch(this.checker.getAvailableTask());
            newTask = searcher.searchTask(durationDiference);
            if (newTask == null) break;
        } while (this.actualDuration < this.MAX_DURATION);
    }


    public DailyTask getDailyTask () {
        return this.day;
    }

}
