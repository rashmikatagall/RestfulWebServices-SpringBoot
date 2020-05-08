package com.district.schools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.district.students.Student;
import com.district.students.StudentService;

@RestController
@RequestMapping("/restapi")
public class schoolController {
	
	Logger logger = LoggerFactory.getLogger(schoolController.class);
	@Autowired
	private schoolService schoolsrv;
	
	@Autowired
	private StudentService studentsrv;
	
	@GetMapping("/schools")
	public List<school> getSchools()
	{
		logger.trace("Get schools called");
		return schoolsrv.getAllSchools();
	}
	
	@GetMapping("/schools/{id}")
	public school getSchoolById(@PathVariable int id)
	{
		logger.trace("Get school by Id called");
		return schoolsrv.getSchoolById(id);
	}
	
	@PostMapping("/schools")
	public void addSchool(@RequestBody school schl) throws Exception {
		try{
			logger.trace("Adding school: " + schl.toString());
			schoolsrv.addSchool(schl);
		}
		catch(Exception e) {
			 throw new Exception(e.getMessage());
		}
		
	}
	
	@GetMapping("/schools/{schoolId}/students")
	public List<Student> getStudents(@PathVariable int schoolId)
	{
		logger.trace("Get students by school Id - " + schoolId + " called");
		return studentsrv.getStudentsBySchId(schoolId);
	}
	
	
	@PostMapping("/schools/{schoolId}/students")
	public void addStudent(@RequestBody Student student, @PathVariable int schoolId) {
		logger.trace("Adding student to schoolId - " + schoolId);
		studentsrv.addStudent(student,schoolId);
		schoolsrv.updateStudentCount(schoolId,1);
		
	}
	
	@GetMapping("/schools/{schoolId}/students/{studentId}")
	public Student getStudentbyId(@PathVariable int schoolId, @PathVariable int studentId)
	{
		logger.trace("Get student by Id called");
		return studentsrv.getStudentById(schoolId,studentId);
	}


}
