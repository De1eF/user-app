package com.example.userapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "role_seq")
    @SequenceGenerator(name = "role_seq",
            sequenceName = "role_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "role_name")
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    public enum RoleName {
        ADMIN,
        USER
    }
}
