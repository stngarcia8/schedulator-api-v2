package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.usecases.TaskGenerator;
import cl.schedulator.api.usecases.shared.TaskSorterEnum;
import cl.schedulator.api.usecases.shared.TaskSummary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1.0")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
public class TaskController {

    @Autowired
    TaskGenerator generator;


    @Operation(summary = "Get a list of task distributed by day and order by task duration")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return task distributed and order by task duration"),
                    @ApiResponse(responseCode = "404", description = "Task service not found, an empty task sumary is returned"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping(value = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasks () {
        log.info("Processing the request at the entrypoint /api/v1.0/tasks");
        TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASK_DURATION);
        if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(summary);
    }


    @Operation(summary = "Get a list of task distributed by day and order by task per day quantity")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Return task distributed and order by task per day quantity"),
                    @ApiResponse(responseCode = "404", description = "Task service not found, an empty task sumary is returned"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            })
    @GetMapping(value = "/tasks/day", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasksByQuantity () {
        log.info("Processing the request at the entrypoint /api/v1.0/tasks");
        TaskSummary summary = this.generator.getSummary(TaskSorterEnum.BY_TASKS_QUANTITY_PER_DAY);
        if (!summary.foundTasks()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(summary);
    }

}
