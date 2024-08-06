package ru.edu.egar.mvcdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.service.StudentService;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "index";
    }

    @GetMapping("/add_student")
    public String getAddStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add_student";
    }

    @PostMapping("/add_student")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_student";
        }

        service.saveStudent(student);
        return "redirect:/";
    }
}
