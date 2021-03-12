package com.vgtstptlk.jwtexampleproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @NotBlank
    private String name;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
