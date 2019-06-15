package com.student.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.student.repositories.models.Course;
import com.student.repositories.models.Student;
import com.student.service.StudentService;
import com.student.service.requests.StudentCourseRequest;
import com.student.service.responses.GenericResponse;

@RestController
@RequestMapping("/StudentService")
public class ELearningController {
	private static Logger log = LoggerFactory.getLogger(ELearningController.class);

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "createCourse", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public GenericResponse registerCourse( @RequestBody Course course) {
		return studentService.createCourse(course);

	}
	
	@RequestMapping(value = "createStudent", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public GenericResponse createStudent( @RequestBody Student student) {
		return studentService.createStudent(student);

	}

	@RequestMapping(value = "registerCourse", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public GenericResponse registerCourse(@Valid @RequestBody StudentCourseRequest registerCourseRequest) {
		return studentService.registerCourse(registerCourseRequest);
	}

	@RequestMapping(value = "unRegisterCourse", method = RequestMethod.POST, consumes = "application/JSON", produces = "application/JSON")
	public GenericResponse unRegisterCourse(@Valid @RequestBody StudentCourseRequest unRegisterCourse) {
		log.info("Atempting unregister with {}", unRegisterCourse);
		return  studentService.UnRegisterCourse(unRegisterCourse);

	}

	@RequestMapping(value = "listAllCourses", method = RequestMethod.GET, produces = "application/JSON")
	public List<Course> listAllCourses() {

		log.info("Atempting view all courses");
		List<Course> allCourses = studentService.viewAllCourses();
		return allCourses;
	}

	@RequestMapping(value = "listCoursesOfStudent/{studentName}", method = RequestMethod.GET, produces = "application/JSON")
	public List<Course> listCoursesOfStudent(@PathVariable String studentName)  {
		log.info("view all courses registed by {}", studentName);
		List<Course> listOfCourses = studentService.listCoursesOfStudent(studentName);

		return listOfCourses;
	}
	
	@ExceptionHandler(RuntimeException.class)
	public final ResponseEntity<?> handleRuntimeExceptions(RuntimeException ex) {
	  // log it
	  return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleExceptions(Exception ex) {
	  // log it
	  return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
