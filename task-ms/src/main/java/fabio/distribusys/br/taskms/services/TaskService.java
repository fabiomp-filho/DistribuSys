package fabio.distribusys.br.taskms.services;

import fabio.distribusys.br.taskms.controllers.client.UserClient;
import fabio.distribusys.br.taskms.domain.dtos.TaskRequestDTO;
import fabio.distribusys.br.taskms.domain.dtos.TaskResponseDTO;
import fabio.distribusys.br.taskms.domain.entities.Task;
import fabio.distribusys.br.taskms.mapper.TaskMapper;
import fabio.distribusys.br.taskms.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(request);

        userClient.CheckUserExists(request.getUser());

        Task entityToCreate = TaskMapper.INSTANCE.toEntity(request);

        System.out.println(entityToCreate);

        Task response = taskRepository.save(entityToCreate);

        System.out.println(response);

        return TaskMapper.INSTANCE.toDTO(response);
    }
}
