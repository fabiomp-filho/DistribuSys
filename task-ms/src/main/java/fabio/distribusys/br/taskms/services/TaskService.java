package fabio.distribusys.br.taskms.services;

import fabio.distribusys.br.taskms.controllers.client.UserClient;
import fabio.distribusys.br.taskms.domain.dtos.TaskRequestDTO;
import fabio.distribusys.br.taskms.domain.dtos.TaskResponseDTO;
import fabio.distribusys.br.taskms.domain.entities.Task;
import fabio.distribusys.br.taskms.mapper.TaskMapper;
import fabio.distribusys.br.taskms.pagination.CustomPage;
import fabio.distribusys.br.taskms.pagination.CustomPageImpl;
import fabio.distribusys.br.taskms.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserClient userClient;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskResponseDTO createTask(TaskRequestDTO request) {

        userClient.CheckUserExists(request.getUser());

        Task entityToCreate = TaskMapper.INSTANCE.toEntity(request);

        Task response = taskRepository.save(entityToCreate);

        return TaskMapper.INSTANCE.toDTO(response);
    }

    @Transactional(readOnly = true)
    public CustomPage<TaskResponseDTO> getAllTasks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Task> entities = taskRepository.findAll(pageable);

        Page<TaskResponseDTO> response = entities.map(TaskMapper.INSTANCE::toDTO);

        return new CustomPageImpl<>(response);
    }

    @Transactional(readOnly = true)
    public CustomPage<TaskResponseDTO> getTasksByUserId(Long userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Task> entities = taskRepository.findTasksByUser(userId, pageable);

        if (entities.isEmpty()) {
            return new CustomPageImpl<>(Page.empty(pageable));
        }

        Page<TaskResponseDTO> response = entities.map(TaskMapper.INSTANCE::toDTO);

        return new CustomPageImpl<>(response);
    }
}
