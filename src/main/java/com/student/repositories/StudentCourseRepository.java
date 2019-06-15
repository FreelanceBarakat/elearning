package com.student.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.repositories.models.StudentCourse;


public interface StudentCourseRepository  extends JpaRepository<StudentCourse, Integer>{
	public List<StudentCourse> findByCourseName(String courseName);
	public List<StudentCourse> findByStudentName(String studentName);
	@Modifying
	@Query("delete from StudentCourse f where f.studentName=:studentName or f.courseName=:courseName")
	public void deleteStudentCourse(@Param("studentName") String studentName, @Param("courseName") String courseName);
}
