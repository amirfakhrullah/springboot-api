package com.springboot.api.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
//	SELECT * FROM student WHERE email = ?
	Optional<Student> findStudentByEmail(String email);
}
