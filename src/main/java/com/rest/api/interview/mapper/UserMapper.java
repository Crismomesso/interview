package com.rest.api.interview.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.rest.api.interview.dto.UserDTO;
import com.rest.api.interview.model.User;

public final class UserMapper {

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User convertToEntity(UserDTO dto) {
        final User entity = new User();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setAdress(dto.getAdress());
        entity.setBirthdate(dto.getBirthDate());
        return entity;
    }

    public static UserDTO convertToDTO(User entitty) {
        final UserDTO dto = new UserDTO();
        dto.setId(entitty.getId());
        dto.setName(entitty.getName());
        dto.setCpf(entitty.getCpf());
        dto.setAdress(entitty.getAdress());
        dto.setBirthDate(entitty.getBirthdate());
        return dto;
    }

    public static List<UserDTO> makeUsersDTOList(Collection<User> users) {
        return users.stream().map(UserMapper::convertToDTO).collect(Collectors.toList());
    }

}