package cl.schedulator.api.usecases.optimizer.distributors;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.shared.mappers.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskChecker {
  private List<Task> originalTaskList;
  private List<String> taskIdCheck;

  private TaskChecker(List<Task> originalTaskList) {
    this.originalTaskList = originalTaskList.stream().collect(Collectors.toList());
    this.taskIdCheck = new ArrayList<>();
  }

  public static TaskChecker createChecker(List<Task> originalTaskList) {
    return new TaskChecker(originalTaskList);
  }

  public Boolean canProcessThisTask(Task task) {
    Optional<String> optTaskId =
        this.taskIdCheck.stream().filter(id -> id.equals(task.getTaskId())).findFirst();
    return !optTaskId.isPresent();
  }

  public void checkIt(Task task) {
    this.taskIdCheck.add(task.getTaskId());
    Optional<Task> foundTask =
        this.originalTaskList.stream()
            .filter(t -> t.getTaskId().equals(task.getTaskId()))
            .findFirst();
    updateTaskStatus(foundTask);
  }

  private void updateTaskStatus(Optional<Task> taskFound) {
    this.originalTaskList.set(
        this.originalTaskList.indexOf(taskFound.get()),
        TaskMapper.create(
                taskFound.get().getTaskId(),
                taskFound.get().getTaskName(),
                taskFound.get().getDuration(),
                false)
            .getTask());
  }

  public List<Task> getAvailableTask() {
    return this.originalTaskList.stream().filter(t -> t.getActive()).collect(Collectors.toList());
  }
}
