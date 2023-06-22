package com.example.userapp.controller;

import com.example.userapp.dto.mapper.UserMapper;
import com.example.userapp.dto.request.UserRequestDto;
import com.example.userapp.dto.response.UserResponseDto;
import com.example.userapp.model.User;
import com.example.userapp.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public List<UserResponseDto> getAll(@RequestParam(defaultValue = "5") Integer count,
                             @RequestParam(defaultValue = "0") Integer page,
                             @RequestParam(defaultValue = "id") String sortBy) {
        Sort sort = Sort.by(sortBy);
        PageRequest pageRequest = PageRequest.of(page, count, sort);
        return userService.findAll(pageRequest)
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user.getStatus().equals(User.Status.INACTIVE)) {
            throw new RuntimeException("User not found");
        }
        return userMapper.mapToDto(user);
    }

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserRequestDto requestDto) {
        User user = userMapper.mapToModel(requestDto);
        return userMapper.mapToDto(userService.add(user));
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid UserRequestDto requestDto) {
        User user = userMapper.mapToModel(requestDto);
        userService.update(id, user);
        return userMapper.mapToDto(user);
    }

    @PatchMapping("/{id}/lock/{lock}")
    public UserResponseDto setActive(@PathVariable Long id,
                                     @PathVariable Boolean lock) {
        User user = userService.findById(id);
        user.setStatus(lock ? User.Status.INACTIVE : User.Status.ACTIVE);
        userService.update(id, user);
        return userMapper.mapToDto(user);
    }
}
