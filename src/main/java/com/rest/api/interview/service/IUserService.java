
package com.rest.api.interview.service;

import java.util.List;

import com.rest.api.interview.dto.UserDTO;
import com.rest.api.interview.exception.ConstraintsViolationException;
import com.rest.api.interview.exception.EntityNotFoundException;
import com.rest.api.interview.exception.InternalServerErrorExeption;

/**
 * IUser
 */
public interface IUserService {
    List<UserDTO> getUsers();

    UserDTO createUser(UserDTO newUser) throws ConstraintsViolationException;

    UserDTO findByName(String name) throws EntityNotFoundException;

    UserDTO findById(Long id) throws EntityNotFoundException;

    void deleteById(Long id) throws InternalServerErrorExeption;

	UserDTO alterUser(long id , UserDTO userDto)throws ConstraintsViolationException, EntityNotFoundException;
}