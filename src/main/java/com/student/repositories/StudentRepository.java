package com.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.repositories.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	public Student findByName(String name);
}
