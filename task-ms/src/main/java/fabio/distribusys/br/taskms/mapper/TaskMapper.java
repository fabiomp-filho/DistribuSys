package fabio.distribusys.br.taskms.mapper;

import fabio.distribusys.br.taskms.domain.dtos.TaskRequestDTO;
import fabio.distribusys.br.taskms.domain.dtos.TaskResponseDTO;
import fabio.distribusys.br.taskms.domain.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toEntity(TaskRequestDTO dto);

    TaskResponseDTO toDTO(Task task);

    void toUpdateEntity(TaskRequestDTO dto, @MappingTarget Task task);

}
