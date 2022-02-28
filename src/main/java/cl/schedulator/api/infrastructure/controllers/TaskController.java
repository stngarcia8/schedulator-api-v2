package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.domain.entities.Task;
import cl.schedulator.api.usecases.TaskGenerator;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Autowired
    TaskGenerator generator;


    @Operation(summary = "Get a list of task distributed by day and order by task duration")
    @ApiResponse(responseCode = "200", description = "List with task distributed", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))})
    @GetMapping(value = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasks () {
        log.info("Processing the request at the entrypoint /api/v1.0/tasks");
        TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASK_DURATION);
        if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(summary);
    }


    @Operation(summary = "Get a list of task distributed by day and order by task per day quantity")
    @ApiResponse(responseCode = "200", description = "List with task distributed", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))})
    @GetMapping(value = "/tasks/day", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasksByQuantity () {
        log.info("Processing the request at the entrypoint /api/v1.0/tasks");
        TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY);
        if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(summary);
    }

}
