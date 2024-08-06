package ru.edu.egar.mvcdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.egar.mvcdemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
