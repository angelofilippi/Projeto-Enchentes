package com.api.enchentes.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename", nullable = false, unique = true, length = 50)
    private String rolename;

    public Long getId() { return id; }

    public String getRolename() { return rolename; }

    public void setId(Long id) { this.id = id; }

    public void setRolename(String rolename) { this.rolename = rolename; }
}
