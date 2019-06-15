package com.student.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.repositories.CourseRepository;
import com.student.repositories.StudentCourseRepository;
import com.student.repositories.StudentRepository;
import com.student.repositories.models.Course;
import com.student.repositories.models.Student;
import com.student.repositories.models.StudentCourse;
import com.student.service.requests.StudentCourseRequest;
import com.student.service.responses.GenericResponse;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentCourseRepository studentCourseRepository;

	final String COURSE_NOT_FOUND = "Course not Found!";
	final String STUDENT_NOT_FOUND = "Student not Found!";
	final int UNSUCCESSFUL_STATUS = -1;

	public GenericResponse registerCourse(StudentCourseRequest registerCourseRequest) {
		GenericResponse response = new GenericResponse(200, "Successful");
		try {
			Object[] studentCourseData = retrieveCourseStudentData(registerCourseRequest);
			Student studentData = (Student) studentCourseData[0];
			Course courseData = (Course) studentCourseData[1];
			
			StudentCourse studentCourse = new StudentCourse(studentData.getName(), courseData.getCourseName());
			courseData.setLastUpdated(new Date().toString()+"");
			
			studentCourseRepository.save(studentCourse);
			courseRepository.save(courseData);
			
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500);
		}
		return response;
	}

	@Transactional
	public GenericResponse UnRegisterCourse(StudentCourseRequest unRegisterCourse) {
		GenericResponse response = new GenericResponse(200, "Successful");
		try {
			Object[] studentCourseData = retrieveCourseStudentData(unRegisterCourse);
			Student studentData = (Student) studentCourseData[0];
			Course courseData = (Course) studentCourseData[1];

			StudentCourse studentCourse = new StudentCourse(studentData.getName(), courseData.getCourseName());

			studentCourseRepository.deleteStudentCourse(studentCourse.getStudentName(), studentCourse.getCourseName());
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(500);
		}

		return response;

	}

	public List<Course> viewAllCourses() {
		return courseRepository.findAll();

	}

	public GenericResponse createCourse(Course course) {
		GenericResponse response = new GenericResponse(200, "Successful");
		try {
			course.setPublishDate(new Date().toString()+""); 
			courseRepository.save(course);
		} catch (Exception e) {
			response.setMessage("Course already Exists");
			response.setStatus(500);
		}
		return response;
	}

	public List<Course> listCoursesOfStudent(String studentName) {
		Student student = studentRepository.findByName(studentName);
		List<StudentCourse> allCoursesNames = studentCourseRepository.findByStudentName(student.getName());
		 List<Course> allCoursesData = new ArrayList<>();
		 Course currentCourse;
		 
		 for(StudentCourse studentCourse : allCoursesNames) {
			 currentCourse = courseRepository.findByCourseName(studentCourse.getCourseName());
			 if(currentCourse != null) {
				 allCoursesData.add(currentCourse);
			 }
		 }
		return allCoursesData;
	}

	public GenericResponse createStudent(Student student) {
		GenericResponse response = new GenericResponse(200, "Successful");
		try {
			studentRepository.save(student);
		} catch (Exception e) {
			response.setMessage("Course already Exists");
			response.setStatus(500);
		}
		return response;
	}

	private Object[] retrieveCourseStudentData(StudentCourseRequest studentCourseRequest) throws Exception {
		Course courseData = courseRepository.findByCourseName(studentCourseRequest.getCourseName());
		Student studentData = studentRepository.findByName(studentCourseRequest.getStudentName());

		if (courseData == null) {
			throw new Exception(COURSE_NOT_FOUND);

		} else if (studentData == null) {
			throw new Exception(STUDENT_NOT_FOUND);
		}

		return new Object[] { studentData, courseData };
	}
}
