package com.example.web1503.repositories;

import com.example.web1503.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s where s.rating = (select max(s1.rating) from Student s1)")
    List<Student> findAllStudentsWithMaxRating();
}