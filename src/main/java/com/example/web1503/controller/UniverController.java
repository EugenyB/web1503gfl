package com.example.web1503.controller;

import com.example.web1503.entities.Faculty;
import com.example.web1503.entities.Student;
import com.example.web1503.repositories.FacultyRepository;
import com.example.web1503.repositories.StudentRepository;
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
    private final StudentRepository studentRepository;

    @GetMapping("/faculties")
    public String faculties(Model model) {
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "faculties";
    }

    @GetMapping("/students")
    public String allStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "allstudents";
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

    @GetMapping("/edit_faculty")
    public String editFaculty(@RequestParam("id") int id, Model model) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            return "redirect:/faculties";
        }
        model.addAttribute("faculty", faculty.get());
        return "edit_faculty";
    }

    @PostMapping("/update_faculty")
    public String updateFaculty(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("director") String director) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isEmpty()) {
            return "redirect:/faculties";
        }
        Faculty f = faculty.get();
        f.setName(name);
        f.setDirector(director);
        facultyRepository.save(f);
        return "redirect:/faculties";
    }

    @GetMapping("/delete_faculty/{id}")
    public String deleteFaculty(@PathVariable("id") int id) {
        facultyRepository.deleteById(id);
        return "redirect:/faculties";
    }

    @GetMapping("/top_rating_student")
    public String topRatingStudent(Model model) {

        List<Student> topStudents = studentRepository.findAllStudentsWithMaxRating();

        model.addAttribute("topStudents", topStudents);
        return "show_student";
    }
}
