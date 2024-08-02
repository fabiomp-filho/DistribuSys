package fabio.distribusys.br.userms.mapper;

import fabio.distribusys.br.userms.domain.dto.UserRequestDTO;
import fabio.distribusys.br.userms.domain.dto.UserResponseDTO;
import fabio.distribusys.br.userms.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO userRequest);

    UserResponseDTO toDTO(User user);

    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);
}
