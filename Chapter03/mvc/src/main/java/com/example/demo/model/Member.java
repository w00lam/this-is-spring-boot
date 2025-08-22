package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String email;
    private  Integer age;
}
