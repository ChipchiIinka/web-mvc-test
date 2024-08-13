package ru.edu.egar.mvcdemo.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
public class StudentDto {

    private Long id;

    @Size(min = 2, max= 50, message = "First name should be from 2 to 50 characters")
    private String firstName;

    @Size(min = 2, max= 50, message = "Last name should be from 2 to 50 characters")
    private String lastName;
    private String patronymic;

    @Pattern(regexp = "^[A-z0-9_.-]+@[A-z.-]+$", message = "Invalid email format")
    private String email;
    private String phone;
    private String address;

    @Range(min = 13, max = 65, message = "Student age should be from 13 to 65 years")
    private Integer age;
}
