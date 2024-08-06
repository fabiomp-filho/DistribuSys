package fabio.distribusys.br.taskms.controllers;

import fabio.distribusys.br.taskms.domain.dtos.TaskRequestDTO;
import fabio.distribusys.br.taskms.domain.dtos.TaskResponseDTO;
import fabio.distribusys.br.taskms.pagination.CustomPage;
import fabio.distribusys.br.taskms.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @GetMapping
    public ResponseEntity<CustomPage<TaskResponseDTO>> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(page, size));
    }

    @GetMapping("/get-by-user/{id}")
    public ResponseEntity<CustomPage<TaskResponseDTO>> getTasksByUser(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByUserId(id, page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK).body("Task with id " + id + " deleted successfully.");
    }
    @DeleteMapping("/delete-tasks-by-user/{id}")
    public ResponseEntity<Void> deleteTaskByUser(@PathVariable Long id){

        taskService.deleteTasksByUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
