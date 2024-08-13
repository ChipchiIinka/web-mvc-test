package ru.edu.egar.mvcdemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.edu.egar.mvcdemo.dto.StudentDto;
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

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Long id, Model model) {
        model.addAttribute("student", service.getStudentById(id));
        return "student";
    }

    @GetMapping("/add_student")
    public String getAddStudentForm(Model model) {
        model.addAttribute("student",  StudentDto.builder().build());
        return "add_student";
    }

    @PostMapping("/add_student")
    public String createStudent(@Valid @ModelAttribute("student") StudentDto studentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_student";
        }
        service.saveStudent(studentDto);
        return "redirect:/";
    }

    @GetMapping("/{id}/update_student")
    public String getAddStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("student",  service.getStudentById(id));
        return "update_student";
    }

    @PutMapping("/{id}/update_student")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") @Valid StudentDto studentDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update_student";
        }
        service.updateStudent(id, studentDto);
        return "redirect:/";
    }
}
