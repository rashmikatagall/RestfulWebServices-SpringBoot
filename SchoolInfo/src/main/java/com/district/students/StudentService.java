package com.district.students;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.district.schools.schoolDB;
import com.district.teachers.TeacherDb;

@Service
public class StudentService {

	@Autowired
	private StudentDb studentDB;
	
	@Autowired
	private schoolDB schoolDb;
	
	@Autowired
	private TeacherDb teacherDb;
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	public List<Student> getStudentsBySchId(int schoolId) {
		
		logger.trace("getStudentsBySchId >> schoolId - " + schoolId);
		List<Student> students = new ArrayList<>();
		Iterator<Student> it = studentDB.findBySchlId(schoolId).iterator();
		while(it.hasNext())
			students.add(it.next());
		logger.trace("getStudentsBySchId << " + students.size() + " students fecthed");
		return students;
		
	}

	public void addStudent(Student student, int schoolId) {
		logger.trace("addStudent >> schoolId - " + schoolId );
		student.setSchl(schoolDb.findById(schoolId).get());
		student.setTeacher(teacherDb.findById(student.getTeacherId()).get());
		studentDB.save(student);
		teacherDb.findById(student.getTeacherId()).get().getStudentSet().add(student);
		logger.trace("addStudent << student saved" );
		
	}

	public Student getStudentById(int schoolId, int studentId) {
		logger.trace("getStudentById >> schoolId - " + schoolId + "studentId - " + studentId);
		Student student = studentDB.findBySchlIdAndId(schoolId, studentId);
		logger.trace("getStudentById << student fetched ");
	      return student;
		
	}

}
