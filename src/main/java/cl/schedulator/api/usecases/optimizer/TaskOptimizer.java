package cl.schedulator.api.usecases.optimizer;

import cl.schedulator.api.usecases.loader.TaskResponseDto;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;

import java.util.List;

public interface TaskOptimizer {

  void transformToEntityTaskList(List<TaskResponseDto> originTaskList);

  TaskSummary getSummary(TaskSorterEnum orderType);
}
