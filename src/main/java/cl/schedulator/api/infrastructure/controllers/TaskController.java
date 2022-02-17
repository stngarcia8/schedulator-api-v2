package cl.schedulator.api.infrastructure.controllers;

import cl.schedulator.api.domain.entities.Task;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {
    private List<Task> taskList = new ArrayList<>();

    @GetMapping(value = "/tasks", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> getTasks() {
        Task task = Task.builder()
                .taskId("xxx1")
                .taskName("tarea 1")
                .duration(3)
                .build();
        this.taskList.add(task);
        return ResponseEntity.ok(taskList);
    }


}
