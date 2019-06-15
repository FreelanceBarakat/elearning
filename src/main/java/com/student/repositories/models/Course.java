package com.student.repositories.models;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"courseName"})})
public class Course implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	String courseName;

	@Column
	@NotNull
	String description;

	@Column
	String lastUpdated;

	@Column
	String instructor;

	@Column
	String publishDate;

	@Column
	Integer totalHours;

	@Transient
	Set<Student> students;

	public Course(int id, String courseName, String description, String lastUpdated, String instructor,
			String publishDate, Integer totalHours, Student... students) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.lastUpdated = lastUpdated;
		this.instructor = instructor;
		this.publishDate = publishDate;
		this.totalHours = totalHours;

		addCourseToStudent(students);
	}

	public Course() {
		super();
	}

	public Course(int id, String courseName, String description, String lastUpdated, String instructor,
			String publishDate, Integer totalHours) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.description = description;
		this.lastUpdated = lastUpdated;
		this.instructor = instructor;
		this.publishDate = publishDate;
		this.totalHours = totalHours;
	}

	public void addCourseToStudent(Student... students) {
		this.students = Stream.of(students).collect(Collectors.toSet());
		this.students.forEach(x -> x.getCourses().add(this));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

}
