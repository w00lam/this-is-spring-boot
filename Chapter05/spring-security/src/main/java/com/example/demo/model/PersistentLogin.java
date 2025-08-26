package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "persistent_logins")
@Data
public class PersistentLogin {
    @Id
    @Column(length = 64)
    private String series;
    @Column(length = 64, nullable = false)
    private String username;
    @Column(length = 64, nullable = false)
    private String token;
    @Column(name = "last_used", nullable = false)
    private LocalDateTime lastUsed;
}
