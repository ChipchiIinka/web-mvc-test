package ru.edu.egar.mvcdemo.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.edu.egar.mvcdemo.dto.StudentDto;
import ru.edu.egar.mvcdemo.service.StudentService;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    private static final String MOCK_STR = "test";

    @MockBean
    private StudentService mockService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    void getIndexTest() {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"))
            .andExpect(model().attributeExists("students"));
    }

    @Test
    @SneakyThrows
    void getAddStudentFormTest() {
        mockMvc.perform(get("/add_student"))
            .andExpect(status().isOk())
            .andExpect(view().name("add_student"))
            .andExpect(model().attributeExists("student"));
    }

    @Test
    @SneakyThrows
    void createStudentTest() {
        mockMvc.perform(
            post("/add_student")
                .param("lastName", MOCK_STR)
                .param("firstName", MOCK_STR)
                .param("patronymic", MOCK_STR)
                .param("email", "blabla@mail.com")
                .param("age", "20")
                .param("phone", "+78005553535")
                .param("address", MOCK_STR))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/"));
    }

    @Test
    @SneakyThrows
    void whenCreateStudentEmailHasErrorTest() {
        mockMvc.perform(
                post("/add_student")
                    .param("lastName", MOCK_STR)
                    .param("firstName", MOCK_STR)
                    .param("patronymic", MOCK_STR)
                    .param("email", "blablamail.com")
                    .param("age", "20")
                    .param("phone", "+78005553535")
                    .param("address", MOCK_STR))
            .andExpect(status().isOk())
            .andExpect(view().name("add_student"))
            .andExpect(model().hasErrors())
            .andExpect(model().attributeHasFieldErrors("student", "email"));
    }

    @Test
    @SneakyThrows
    void getStudentTest() {
        StudentDto found = StudentDto.builder()
                .id(1L)
                .firstName(MOCK_STR)
                .lastName(MOCK_STR)
                .patronymic(MOCK_STR)
                .email("blabla@mail.com")
                .age(20)
                .phone("+78005553535")
                .address(MOCK_STR)
                .build();
        given(mockService.getStudentById(1L)).willReturn(found);
        mockMvc.perform(get("/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("student"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    @SneakyThrows
    void getUpdateStudentFormTest() {
        StudentDto found = StudentDto.builder()
                .id(1L)
                .firstName(MOCK_STR)
                .lastName(MOCK_STR)
                .patronymic(MOCK_STR)
                .email("blabla@mail.com")
                .age(20)
                .phone("+78005553535")
                .address(MOCK_STR)
                .build();
        given(mockService.getStudentById(1L)).willReturn(found);
        mockMvc.perform(get("/1/update_student"))
                .andExpect(status().isOk())
                .andExpect(view().name("update_student"))
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    @SneakyThrows
    void updateStudentTest() {
        mockMvc.perform(
                put("/1/update_student")
                        .param("lastName", MOCK_STR)
                        .param("firstName", MOCK_STR)
                        .param("patronymic", MOCK_STR)
                        .param("email", "blabla@mail.com")
                        .param("age", "20")
                        .param("phone", "+78005553535")
                        .param("address", MOCK_STR))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));
    }

    @Test
    @SneakyThrows
    void whenUpdateStudentEmailHasErrorTest() {
        mockMvc.perform(
                put("/1/update_student")
                        .param("lastName", MOCK_STR)
                        .param("firstName", MOCK_STR)
                        .param("patronymic", MOCK_STR)
                        .param("email", "blablamail.com")
                        .param("age", "20")
                        .param("phone", "+78005553535")
                        .param("address", MOCK_STR))
                .andExpect(status().isOk())
                .andExpect(view().name("update_student"))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("student", "email"));
    }
}
