package com.example.userapp.dto.mapper;

import com.example.userapp.dto.response.RoleResponseDto;
import com.example.userapp.model.UserRole;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponseDto mapToDto(UserRole role) {
        RoleResponseDto responseDto = new RoleResponseDto();
        responseDto.setId(role.getId());
        responseDto.setName(role.getRoleName().name());
        return responseDto;
    }
}
