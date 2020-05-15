package com.district.teachers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeacherDb extends CrudRepository<Teacher,Integer> {

	public List<Teacher> findAllBySchlId(int id);
	
	public Teacher findBySchlIdAndId(int schoolId, int id);
}
