package cl.schedulator.api.usecases.optimizer;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.optimizer.distributors.TaskDistributor;
import cl.schedulator.api.usecases.optimizer.transformers.TaskTransform;
import cl.schedulator.api.usecases.shared.DailyTask;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TaskOptimizerImpl implements TaskOptimizer {
  private List<Task> tasks;

  public TaskOptimizerImpl() {
    this.tasks = new ArrayList<>();
  }

  @Override
  public void transformToEntityTaskList(List<TaskResponseDto> originTaskList) {
    TaskTransform transformer = TaskTransform.processList(originTaskList);
    this.tasks = transformer.getTaskList();
  }

  @Override
  public TaskSummary getSummary(TaskSorterEnum orderType) {
    TaskDistributor distributor = TaskDistributor.distributeTasks(this.tasks);
    if (orderType == TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY) {
      Collections.sort(distributor.getSummary().getDays());
      reasigmentDayNumberInSummary(distributor.getSummary());
    }
    return distributor.getSummary();
  }

  private void reasigmentDayNumberInSummary(TaskSummary summary) {
    Integer dayCounter = 1;
    for (DailyTask d : summary.getDays()) {
      d.setDayNumber(dayCounter);
      dayCounter++;
    }
  }
}
