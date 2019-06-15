package com.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.repositories.models.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	public Course findByCourseName(String courseName);
}
