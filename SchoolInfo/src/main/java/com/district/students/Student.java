package com.district.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.hateoas.RepresentationModel;

import com.district.schools.school;
import com.district.teachers.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENTS")
public class Student extends RepresentationModel<Student>{

	@Id
	private int id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SCHOOL_ID")
	@JsonIgnore
	private school schl;
	@ManyToOne
	@JoinColumn(name ="TEACHERS_ID")
	@NotFound(action = NotFoundAction.IGNORE)

	private Teacher teacher;
	
	private int teacherId;
	
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
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
	public school getSchl() {
		return schl;
	}
	public void setSchl(school schl) {
		this.schl = schl;
	}
	
	
}
