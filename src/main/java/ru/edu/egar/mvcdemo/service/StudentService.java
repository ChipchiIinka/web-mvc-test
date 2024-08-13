package ru.edu.egar.mvcdemo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.edu.egar.mvcdemo.dto.StudentDto;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.repo.StudentRepository;
import ru.edu.egar.mvcdemo.service.mapper.StudentMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public void saveStudent(StudentDto studentDto) {
        Student student = new Student();
        repository.save(mapper.toEntity(studentDto, student));
    }

    public List<StudentDto> getAllStudents() {
        return mapper.toListDto(repository.findAll());
    }

    public StudentDto getStudentById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found")));
    }

    @Transactional
    public void updateStudent(Long id, StudentDto updateStudent) {
        Student repoStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        repository.save(mapper.toEntity(updateStudent, repoStudent));
    }
}
