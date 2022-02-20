package com.springboot.api.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> emailExist =  studentRepository.findStudentByEmail(student.getEmail());
		
		if (emailExist.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		
		if (!exists) {
			throw new IllegalStateException(
					"Student with id " + studentId + " doesn't exist"
					);
		}
		
		studentRepository.deleteById(studentId);
	}

	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException(
						"Student with id " + studentId + "doesn't exists"
						));
		
		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> emailTaken = studentRepository.findStudentByEmail(email);
			
			if (emailTaken.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			
			student.setEmail(email);
		}
	}

}
