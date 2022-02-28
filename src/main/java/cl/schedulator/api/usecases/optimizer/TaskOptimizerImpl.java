package cl.schedulator.api.usecases.optimizer;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.optimizer.distributors.TaskDistributor;
import cl.schedulator.api.usecases.optimizer.transformers.TaskTransform;
import cl.schedulator.api.usecases.shared.DailyTask;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TaskOptimizerImpl implements TaskOptimizer {

    private List<Task> tasks;


    public TaskOptimizerImpl () {
        this.tasks = new ArrayList<>();
    }


    @Override
    public void transformToEntityTaskList (List<TaskResponseDto> originTaskList) {
        log.info("Transform original task list into valid task list.");
        TaskTransform transformer = TaskTransform.processList(originTaskList);
        this.tasks = transformer.getTaskList();
    }


    @Override
    public TaskSummary getSummary (TaskSorterEnum orderType) {
        TaskDistributor distributor = TaskDistributor.distributeTasks(this.tasks);
        if (orderType == TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY) {
            Collections.sort(distributor.getSummary()
                    .getDays());
            reasigmentDayNumberInSummary(distributor.getSummary());
        }
        log.info("Task distribution was finished");
        return distributor.getSummary();
    }


    private List<DailyTask> sortByDayTaskQuantity (TaskSummary summary) {
        log.info("Sorting task list");
        return summary.getDays()
                .stream()
                .sorted(Comparator.comparingInt(DailyTask::getTaskPerDay)
                        .reversed())
                .collect(Collectors.toList());
    }


    private void reasigmentDayNumberInSummary (TaskSummary summary) {
        log.info("Reorganizing days order");
        Integer dayCounter = 1;
        for (DailyTask d : summary.getDays()) {
            d.setDayNumber(dayCounter);
            dayCounter++;
        }
    }

}
