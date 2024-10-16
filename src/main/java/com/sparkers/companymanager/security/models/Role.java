package com.sparkers.companymanager.security.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
