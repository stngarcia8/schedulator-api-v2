package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.usecases.TaskGenerator;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1.0")
public class TaskController {

  @Autowired TaskGenerator generator;

  @GetMapping(
      value = "/tasks",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> getTasks() {
    log.info("Processing the request at the entrypoint /api/v1.0/tasks");
    TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASK_DURATION);
    if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok(summary);
  }

  @GetMapping(
      value = "/tasks/bytaskquantity",
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> getTasksByQuantity() {
    log.info("Processing the request at the entrypoint /api/v1.0/tasks");
    TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY);
    if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok(summary);
  }
}
