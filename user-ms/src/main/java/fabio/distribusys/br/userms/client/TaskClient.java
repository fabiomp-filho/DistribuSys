package fabio.distribusys.br.userms.client;


import fabio.distribusys.br.userms.config.FeignConfig;
import fabio.distribusys.br.userms.domain.dto.client.TaskResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "tasks-ms", url = "${task.service.url}", configuration = FeignConfig.class)
public interface TaskClient {

    @GetMapping("/tasks/get-by-user/{id}")
    Page<TaskResponseDTO> getTasksByUser(
            @PathVariable
            Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size);

    @DeleteMapping("/tasks/delete-tasks-by-user/{id}")
    void deleteTaskByUser(@PathVariable Long id);
}
