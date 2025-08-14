package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member", indexes = {
        @Index(name = "idx_name_age", columnList = "name, age"),
        @Index(name = "idx_email", columnList = "email")
})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "age", nullable = false, columnDefinition = "INTEGER DEFAULT 10")
    private Integer age;
    @Transient
    private String address;
    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Article> articles;
}
