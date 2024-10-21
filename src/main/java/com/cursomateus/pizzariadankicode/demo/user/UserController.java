package com.cursomateus.pizzariadankicode.demo.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private final UserService _service;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto dto, UriComponentsBuilder uriBuilder) {
        UserDto userDto = _service.CreateUser(dto);
        URI endereco = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(endereco).body(userDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(@PageableDefault(size = 10) Pageable paginacao) {
        Page<UserDto> users = _service.getUsers(paginacao);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable @NotNull Long id) {
        UserDto userDto = _service.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto dto, @PathVariable @NotNull Long id) {
        UserDto usuarioAtualizado = _service.updateUser(dto, id);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @NotNull Long id) {
        _service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
