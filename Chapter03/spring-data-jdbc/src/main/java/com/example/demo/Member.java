package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
