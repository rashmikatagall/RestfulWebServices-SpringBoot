package com.district.students;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentDb extends CrudRepository<Student, Integer> {
	
	public List<Student> findBySchlId(int id);
	
	public Student findBySchlIdAndId(int schlId, int id);

}
