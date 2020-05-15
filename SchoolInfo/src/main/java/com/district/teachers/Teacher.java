package com.district.teachers;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.hateoas.RepresentationModel;

import com.district.schools.school;
import com.district.students.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Teacher extends RepresentationModel<Teacher> {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int classId;
	@NotFound(action = NotFoundAction.IGNORE)
	private int students;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="SCHOOL_ID")
	@JsonIgnore
	private school schl;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TEACHER_STUDENT",joinColumns=@JoinColumn(name="TEACHER_ID"),
	                                    inverseJoinColumns=@JoinColumn(name="STUDENT_ID"))  
	@JsonIgnore
	private Set<Student> studentSet;
	
	public Set<Student> getStudentSet() {
		return studentSet;
	}
	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
	}
	public school getSchl() {
		return schl;
	}
	public void setSchl(school schl) {
		this.schl = schl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public int getStudents() {
		return students;
	}
	public void setStudents(int students) {
		this.students = students;
	}
	
	
	
}
