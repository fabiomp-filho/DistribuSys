package fabio.distribusys.br.userms.controllers;

import fabio.distribusys.br.userms.domain.dto.UserRequestDTO;
import fabio.distribusys.br.userms.domain.dto.UserResponseDTO;
import fabio.distribusys.br.userms.pagination.CustomPage;
import fabio.distribusys.br.userms.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }

    @GetMapping
    public ResponseEntity<CustomPage<UserResponseDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(page, size));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserResponseDTO>> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO request) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, request));
    }
}
