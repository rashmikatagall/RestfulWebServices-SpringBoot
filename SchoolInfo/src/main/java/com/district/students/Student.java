package com.district.students;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.district.schools.school;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENTS")
public class Student {

	@Id
	private int id;
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="SCHOOL_ID")
	@JsonIgnore
	private school schl;
	
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
