package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.usecases.TaskGenerator;
import cl.schedulator.api.usecases.TaskGeneratorImp;
import cl.schedulator.api.usecases.loader.TaskResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0")
public class TaskController {
    private final TaskGenerator taskGenerator;
    private List<TaskResponseDto> taskList = new ArrayList<>();

    @Autowired
    public  TaskController(TaskGeneratorImp taskGenerator){
        this.taskGenerator= taskGenerator;
    }

    @GetMapping(value = "/xxx_tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasks() {
        return ResponseEntity.ok(taskGenerator.getTaskList());
    }


}
