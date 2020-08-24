package com.rest.api.interview.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.util.List;

import javax.validation.Valid;

import com.rest.api.interview.dto.UserDTO;
import com.rest.api.interview.exception.ConstraintsViolationException;
import com.rest.api.interview.exception.EntityNotFoundException;
import com.rest.api.interview.exception.InternalServerErrorExeption;
import com.rest.api.interview.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    @ApiOperation(value = "List All Users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDTO> getAll() throws InternalServerErrorExeption {
        try {
            return userService.getUsers();
        } catch (Exception e) {
            throw new InternalServerErrorExeption("internal error");
        }
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "List user by name")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO getUser(@PathVariable String name) throws EntityNotFoundException {
        return userService.findByName(name);
    }

    @PostMapping
    @ApiOperation(value = "Insert user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO createUser( @RequestBody UserDTO userDto) throws ConstraintsViolationException {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Insert user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO alterUser(@PathVariable long id,  @RequestBody UserDTO userDto)
            throws ConstraintsViolationException, EntityNotFoundException {
        return userService.alterUser(id, userDto);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find User by id")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDTO getUser(@PathVariable Long id) throws EntityNotFoundException {
        return userService.findById(id);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id) throws InternalServerErrorExeption {
        userService.deleteById(id);
    }

}