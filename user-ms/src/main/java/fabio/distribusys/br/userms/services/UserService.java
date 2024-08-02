package fabio.distribusys.br.userms.services;

import fabio.distribusys.br.userms.domain.dto.UserRequestDTO;
import fabio.distribusys.br.userms.domain.dto.UserResponseDTO;
import fabio.distribusys.br.userms.domain.entities.User;
import fabio.distribusys.br.userms.exceptions.BusinessException;
import fabio.distribusys.br.userms.mapper.UserMapper;
import fabio.distribusys.br.userms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserResponseDTO createUser(UserRequestDTO userRequest) {

        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new BusinessException("The email provided is already in use. Please choose another email.");
        }

        User userToCreate = UserMapper.INSTANCE.toEntity(userRequest);

        User createdUser = userRepository.save(userToCreate);

        return UserMapper.INSTANCE.toDTO(createdUser);
    }
}
