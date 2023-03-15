package com.example.web1503.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "director", length = 20)
    private String director;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "faculty_id")
    private Set<Student> students = new LinkedHashSet<>();

}