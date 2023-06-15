package com.example.userapp.dto.mapper;

import com.example.userapp.dto.request.UserRequestDto;
import com.example.userapp.dto.response.RoleResponseDto;
import com.example.userapp.dto.response.UserResponseDto;
import com.example.userapp.model.User;
import com.example.userapp.model.UserRole;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final RoleMapper roleMapper;

    public UserResponseDto mapToDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setLastName(user.getLastName());
        userResponseDto.setStatus(user.getStatus().name());
        userResponseDto.setCreatedAt(user.getCreatedAt());
        List<RoleResponseDto> roles = user.getRoles()
                .stream()
                .map(roleMapper::mapToDto)
                .toList();
        userResponseDto.setRoles(roles.stream().map(RoleResponseDto::getName).toList());
        return userResponseDto;
    }

    public User mapToModel(UserRequestDto requestDto, String createdAt) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setRoles(new HashSet<>());
        user.setStatus(User.Status.ACTIVE);
        user.setCreatedAt(createdAt);
        return user;
    }
}
