package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.shared.TaskSummary;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TaskDistributor {

    private final Integer MAX_DURATION = 8;
    private final List<Task> originalTaskList;
    private final TaskSummary summary;


    private TaskDistributor (List<Task> originalTaskList) {
        this.originalTaskList = originalTaskList;
        this.summary = new TaskSummary(this.originalTaskList.size());
        this.executeDistribution();
    }


    public static TaskDistributor distributeTasks (List<Task> originalTaskList) {
        return new TaskDistributor(originalTaskList);
    }


    private void executeDistribution () {
        log.info("Starting the distribution for tasks");
        TaskChecker checker = TaskChecker.createChecker(this.originalTaskList);
        for (Task t : this.originalTaskList) {
            if (checker.canProcessThisTask(t)) {
                DailyDistributor distributor =
                        DailyDistributor.processDay(this.summary.getTotalDays() + 1, checker);
                distributor.addTaskToDay(t);
                this.summary.addDay(distributor.getDailyTask());
            }
        }
    }


    public TaskSummary getSummary () {
        return this.summary;
    }

}
