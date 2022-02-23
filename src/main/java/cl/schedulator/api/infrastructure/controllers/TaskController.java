package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.usecases.TaskGenerator;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1.0")
public class TaskController {

    @Autowired
    TaskGenerator generator;

    @GetMapping(value = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasks() {
        log.info("Processing the request at the entrypoint /api/v1.0/tasks");
        List<TaskResponseDto> taskList = this.generator.getTaskList();
        if (taskList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(taskList);
    }


}
