package ru.edu.egar.mvcdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Range;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, max= 50, message = "Last name should be from 2 to 50 characters")
    private String lastName;

    @Size(min = 2, max= 50, message = "First name should be from 2 to 50 characters")
    private String firstName;
    private String patronymic;

    @Pattern(regexp = "^[A-z0-9_.-]+@[A-z.-]+$", message = "Invalid email format")
    private String email;
    private String phone;
    private String address;

    @Range(min = 13, max = 65, message = "Student age should be from 13 to 65 years")
    private Integer age;
}
