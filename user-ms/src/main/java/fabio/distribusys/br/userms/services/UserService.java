package fabio.distribusys.br.userms.services;

import fabio.distribusys.br.userms.domain.dto.UserRequestDTO;
import fabio.distribusys.br.userms.domain.dto.UserResponseDTO;
import fabio.distribusys.br.userms.domain.entities.User;
import fabio.distribusys.br.userms.exceptions.BusinessException;
import fabio.distribusys.br.userms.exceptions.NotFoundException;
import fabio.distribusys.br.userms.mapper.UserMapper;
import fabio.distribusys.br.userms.pagination.CustomPage;
import fabio.distribusys.br.userms.pagination.CustomPageImpl;
import fabio.distribusys.br.userms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserResponseDTO createUser(UserRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("The email provided is already in use. Please choose another email.");
        }

        User entityToCreate = UserMapper.INSTANCE.toEntity(request);

        User response = userRepository.save(entityToCreate);

        return UserMapper.INSTANCE.toDTO(response);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {

        User entity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " Not found."));

        if (!entity.getEmail().equals(request.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new BusinessException("The email provided is already in use. Please choose another email.");
            }
        }

        UserMapper.INSTANCE.updateEntityFromDto(request, entity);

        return UserMapper.INSTANCE.toDTO(entity);
    }

    @Transactional(readOnly = true)
    public CustomPage<UserResponseDTO> getAllUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<User> entities = userRepository.findAll(pageable);

        Page<UserResponseDTO> response = entities.map(UserMapper.INSTANCE::toDTO);

        return new CustomPageImpl<>(response);
    }

    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> getUserById(Long id) {

        User entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " Not found."));

        return Optional.ofNullable(UserMapper.INSTANCE.toDTO(entity));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " Not found."));

        userRepository.delete(entity);
    }
}
