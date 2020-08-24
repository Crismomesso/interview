package com.rest.api.interview.service.impl;

import java.util.List;

import com.rest.api.interview.dto.UserDTO;
import com.rest.api.interview.exception.ConstraintsViolationException;
import com.rest.api.interview.exception.EntityNotFoundException;
import com.rest.api.interview.exception.InternalServerErrorExeption;
import com.rest.api.interview.mapper.UserMapper;
import com.rest.api.interview.model.User;
import com.rest.api.interview.repository.IUserRepository;
import com.rest.api.interview.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(IUserService.class);

    @Autowired
    private IUserRepository userRepo;

    @Override
    public List<UserDTO> getUsers() {
        return UserMapper.makeUsersDTOList(userRepo.findAll());
    }

    @Override
    public UserDTO findByName(String name) throws EntityNotFoundException {
        User user = userRepo.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found" + name));
        return UserMapper.convertToDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO newUser) throws ConstraintsViolationException {
        User model = userRepo.save(UserMapper.convertToEntity(newUser));
        try {
            model = userRepo.save(model);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException to create user: {}", model, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return UserMapper.convertToDTO(model);
    }

    @Override
    public UserDTO findById(Long id) throws EntityNotFoundException {
        User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return UserMapper.convertToDTO(user);
    }

    @Override
    public void deleteById(Long id) throws InternalServerErrorExeption {
        try {
            userRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new InternalServerErrorExeption("Error when the system try to delete user");
        }
    }

    @Override
    public UserDTO alterUser(long id, UserDTO userDto) throws ConstraintsViolationException, EntityNotFoundException {
        User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        user.setAdress(userDto.getAdress());
        user.setBirthdate(userDto.getBirthDate());
        user.setName(userDto.getName());
        user.setAdress(userDto.getAdress());
        user.setCpf(userDto.getCpf());
        return UserMapper.convertToDTO(userRepo.save(user));
    }
}