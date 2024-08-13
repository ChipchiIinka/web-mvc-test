package ru.edu.egar.mvcdemo.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.edu.egar.mvcdemo.dto.StudentDto;
import ru.edu.egar.mvcdemo.entity.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    void toListDto_ShouldReturnListOfStudentDtos() {
        Student student1 = createStudent(1L, "fname", "lname", "patronymic", 20, "maill@maill.com", "12-12-12", "qwerty");
        Student student2 = createStudent(2L, "fname2", "lname2", "patronymic2", 22, "maill2@maill.com", "12-12-22", "qwerty2");

        List<Student> students = List.of(student1, student2);

        List<StudentDto> studentDtos = mapper.toListDto(students);

        assertNotNull(studentDtos);
        assertEquals(2, studentDtos.size());
        assertEquals(student1.getId(), studentDtos.get(0).getId());
        assertEquals(student2.getId(), studentDtos.get(1).getId());
    }

    @Test
    void toDto_ShouldReturnStudentDto() {
        Student student = createStudent(1L, "fname", "lname", "patronymic", 20, "maill@maill.com", "12-12-12", "qwerty");

        StudentDto studentDto = mapper.toDto(student);

        assertNotNull(studentDto);
        assertEquals(student.getId(), studentDto.getId());
        assertEquals(student.getFirstName(), studentDto.getFirstName());
        assertEquals(student.getLastName(), studentDto.getLastName());
        assertEquals(student.getPatronymic(), studentDto.getPatronymic());
        assertEquals(student.getAge(), studentDto.getAge());
        assertEquals(student.getEmail(), studentDto.getEmail());
        assertEquals(student.getPhone(), studentDto.getPhone());
        assertEquals(student.getAddress(), studentDto.getAddress());
    }

    @Test
    void toEntity_ShouldUpdateStudentEntity() {
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("fname")
                .lastName("lname")
                .patronymic("patronymic")
                .age(20)
                .email("maill@maill.com")
                .phone("12-12-12")
                .address("qwerty")
                .build();

        Student student = new Student();
        student.setId(1L);

        Student updatedStudent = mapper.toEntity(studentDto, student);

        assertNotNull(updatedStudent);
        assertEquals(studentDto.getFirstName(), updatedStudent.getFirstName());
        assertEquals(studentDto.getLastName(), updatedStudent.getLastName());
        assertEquals(studentDto.getPatronymic(), updatedStudent.getPatronymic());
        assertEquals(studentDto.getAge(), updatedStudent.getAge());
        assertEquals(studentDto.getEmail(), updatedStudent.getEmail());
        assertEquals(studentDto.getPhone(), updatedStudent.getPhone());
        assertEquals(studentDto.getAddress(), updatedStudent.getAddress());
    }

    private Student createStudent(Long id, String firstName, String lastName, String patronymic, int age, String email, String phone, String address) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPatronymic(patronymic);
        student.setAge(age);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAddress(address);
        return student;
    }
}