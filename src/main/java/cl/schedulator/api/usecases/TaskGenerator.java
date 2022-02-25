package cl.schedulator.api.usecases;

import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;

public interface TaskGenerator {
  TaskSummary getSummary(TaskSorterEnum orderType);
}
