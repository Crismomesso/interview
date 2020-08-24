package com.rest.api.interview.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String adress;
    private Date birthDate;
    private String cpf;
}