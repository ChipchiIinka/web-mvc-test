package ru.edu.egar.mvcdemo.service.mapper;

import org.springframework.stereotype.Service;
import ru.edu.egar.mvcdemo.dto.StudentDto;
import ru.edu.egar.mvcdemo.entity.Student;

import java.util.List;

@Service
public class StudentMapper {

    public List<StudentDto> toListDto(List<Student> students) {
        return students.stream().map(this::toDto).toList();
    }

    public StudentDto toDto(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .patronymic(student.getPatronymic())
                .age(student.getAge())
                .email(student.getEmail())
                .phone(student.getPhone())
                .address(student.getAddress())
                .build();
    }

    public Student toEntity(StudentDto studentDto, Student student) {
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setPatronymic(studentDto.getPatronymic());
        student.setAge(studentDto.getAge());
        student.setEmail(studentDto.getEmail());
        student.setPhone(studentDto.getPhone());
        student.setAddress(studentDto.getAddress());
        return student;
    }
}
