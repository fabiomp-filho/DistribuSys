package fabio.distribusys.br.taskms.services;

import fabio.distribusys.br.taskms.controllers.client.UserClient;
import fabio.distribusys.br.taskms.domain.dtos.TaskRequestDTO;
import fabio.distribusys.br.taskms.domain.dtos.TaskResponseDTO;
import fabio.distribusys.br.taskms.domain.entities.Task;
import fabio.distribusys.br.taskms.exceptions.BusinessException;
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

import java.util.List;

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

        System.out.println(response.getContent());
        return new CustomPageImpl<>(response);
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(Long id) {
        Task entity = taskRepository.findById(id).orElseThrow(() -> new BusinessException("Task with " + id + " Not found."));

        return TaskMapper.INSTANCE.toDTO(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteTask(Long id) {

        Task entity = taskRepository.findById(id).orElseThrow(() -> new BusinessException("Task with id " + id + " not found."));

        taskRepository.delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteTasksByUser(Long id) {

        userClient.CheckUserExists(id);

        List<Task> entities = taskRepository.findTasksByUser(id);

        taskRepository.deleteAll(entities);
    }
}
