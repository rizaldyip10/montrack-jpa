package com.purwadhika.montrackv2.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String pin;
}
