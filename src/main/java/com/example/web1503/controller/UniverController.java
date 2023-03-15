package com.example.web1503.controller;

import com.example.web1503.entities.Faculty;
import com.example.web1503.repositories.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UniverController {

    FacultyRepository facultyRepository;

    @GetMapping("/faculties")
    public String faculties(Model model) {
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "faculties";
    }

    @PostMapping("/add_faculty")
    public String addFaculty(@RequestParam String name, @RequestParam String director) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setDirector(director);
        facultyRepository.save(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("/faculty_students/{id}")
    public String studentsByFaculty(@PathVariable("id") int id, Model model) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            return "redirect:/faculties";
        }
        model.addAttribute("faculty", faculty.get());
        model.addAttribute("students", faculty.get().getStudents());
        return "students_by_faculty";
    }
}
