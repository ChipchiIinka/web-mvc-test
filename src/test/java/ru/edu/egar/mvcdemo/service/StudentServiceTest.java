package ru.edu.egar.mvcdemo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.edu.egar.mvcdemo.dto.StudentDto;
import ru.edu.egar.mvcdemo.entity.Student;
import ru.edu.egar.mvcdemo.repo.StudentRepository;
import ru.edu.egar.mvcdemo.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper mapper;

    @InjectMocks
    private StudentService service;

    @Test
    void saveStudent_ShouldSaveStudent() {
        StudentDto studentDto = StudentDto.builder().build();
        Student student = new Student();

        when(mapper.toEntity(studentDto, student)).thenReturn(student);
        service.saveStudent(studentDto);
        verify(repository, times(1)).save(student);
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudentDtos() {
        List<Student> students = List.of(new Student(), new Student());
        List<StudentDto> studentDtos = List.of(StudentDto.builder().build(), StudentDto.builder().build());

        when(repository.findAll()).thenReturn(students);
        when(mapper.toListDto(students)).thenReturn(studentDtos);

        List<StudentDto> result = service.getAllStudents();

        assertEquals(studentDtos.size(), result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getStudentById_ShouldReturnStudentDto_WhenStudentExists() {
        Long id = 1L;
        Student student = new Student();
        StudentDto studentDto = StudentDto.builder().build();

        when(repository.findById(id)).thenReturn(Optional.of(student));
        when(mapper.toDto(student)).thenReturn(studentDto);

        StudentDto result = service.getStudentById(id);

        assertEquals(studentDto, result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getStudentById_ShouldThrowException_WhenStudentNotFound() {
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getStudentById(id));

        assertEquals("Student not found", exception.getMessage());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void updateStudent_ShouldUpdateStudent() {
        Long id = 1L;
        StudentDto updateStudentDto = StudentDto.builder().build();
        Student repoStudent = new Student();

        when(repository.findById(id)).thenReturn(Optional.of(repoStudent));
        when(mapper.toEntity(updateStudentDto, repoStudent)).thenReturn(repoStudent);

        service.updateStudent(id, updateStudentDto);

        verify(repository, times(1)).save(repoStudent);
    }

    @Test
    void updateStudent_ShouldThrowException_WhenStudentNotFound() {
        Long id = 1L;
        StudentDto updateStudentDto = StudentDto.builder().build();

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.updateStudent(id, updateStudentDto));

        assertEquals("Student not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}