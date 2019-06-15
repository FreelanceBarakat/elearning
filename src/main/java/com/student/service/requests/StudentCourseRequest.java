package com.student.service.requests;


public class StudentCourseRequest {
	private String courseName;
	private String studentName;
	
	public StudentCourseRequest() {
		super();
	}

	
	public StudentCourseRequest(String courseName, String studentName) {
		super();
		this.courseName = courseName;
		this.studentName = studentName;
	}


	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	
	
}
