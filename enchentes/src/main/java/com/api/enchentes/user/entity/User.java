package com.api.enchentes.user.entity;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // faz a relação com a tabela de roles
    @JoinColumn(name = "role_id") // chave estrangeira que referencia a tabela de roles
    private Role role;

    @Column(name = "username", nullable = false, unique = true, length = 120)
    private String username;

    @Column(name = "password", nullable = false, length = 120)
    private String password;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    public Long getId() { return id; }

    public Role getRole() { return role; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public OffsetDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }

    public void setRole(Role role) { this.role = role; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}