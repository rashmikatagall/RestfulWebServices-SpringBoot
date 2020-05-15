package com.district.teachers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.district.schools.school;
import com.district.schools.schoolDB;

@Service
public class TeacherService {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TeacherDb teacherDb;
	
	@Autowired
	schoolDB schoolDb;
	
	public List<Teacher> getTeachersBySchlId(int schoolId) {
	
		logger.trace("getTeachersBySchlId >> schoolId - " + schoolId);
		List<Teacher> teachers = teacherDb.findAllBySchlId(schoolId);
		logger.trace("getTeachersBySchlId << " + teachers.size() + " students fecthed");
		return teachers;
	}

	public void addTeacher(Teacher teacher, int schoolId) {
		logger.trace("addTeacher >> schoolId - " + schoolId);
		teacher.setSchl(schoolDb.findById(schoolId).get());
		teacherDb.save(teacher);
		logger.trace("addTeacher << "+ teacher +"saved");
		
	}

	public Teacher getTeacherById(int schoolId, int teacherId) {
		logger.trace("getTeachersById >> schoolId - " + schoolId + " teacherId - " + teacherId);
		Teacher teacher = teacherDb.findBySchlIdAndId(schoolId, teacherId);
		logger.trace("getTeachersById << teacher found ");
		return teacher;
	}

	public void updateStudentCount(int teacherId, int i) {
		logger.trace("updateStudentCount >> increasing student count by for teacher  "+i);
		Teacher teacher = teacherDb.findById(teacherId).get();
		teacher.setStudents(teacher.getStudents()+i);
		teacherDb.save(teacher);
		logger.trace("updateStudentCount << student count updated for " + teacher.getName());
		
		
	}

	
}
