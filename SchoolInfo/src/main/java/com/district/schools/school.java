package com.district.schools;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.district.address.Address;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Table(name = "SCHOOLS")
public class school extends RepresentationModel<school>{
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "NO_OF_STUDENTS")
	@NotFound(action = NotFoundAction.IGNORE)
	private int students;
	@Column(name = "NO_OF_TEACHERS")
	@NotFound(action = NotFoundAction.IGNORE)
	private int teachers;
	@OneToOne
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;
	
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
	public int getStudents() {
		return students;
	}
	public void setStudents(int students) {
		this.students = students;
	}
	public int getTeachers() {
		return teachers;
	}
	public void setTeachers(int teachers) {
		this.teachers = teachers;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString()
	{
		return this.getName() + " " +this.getAddress();	
	}

}
