package com.district.schools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.district.students.Student;
import com.district.students.StudentService;
import com.district.teachers.Teacher;
import com.district.teachers.TeacherService;

@RestController
@RequestMapping("/restapi")
public class schoolController {
	
	Logger logger = LoggerFactory.getLogger(schoolController.class);
	@Autowired
	private schoolService schoolsrv;
	
	@Autowired
	private StudentService studentsrv;
	
	@Autowired
	private TeacherService teachersrv;
	
	@GetMapping("/schools")
	public CollectionModel<school> getSchools()
	{
		logger.trace("Get schools called");
		List<school> schools = schoolsrv.getAllSchools();
		
		for(school schl : schools)
		{
			Link schlLink = linkTo(methodOn(this.getClass()).getSchoolById(schl.getId()))
                                  .withSelfRel();
			
			Link studentLink = linkTo(methodOn(this.getClass()).getSchoolById(schl.getId())).slash("/students")
					              .withRel("students");
			
			Link teacherLink = linkTo(methodOn(this.getClass()).getSchoolById(schl.getId())).slash("/teachers")
		              .withRel("teachers");
			
			schl.add(schlLink);
			schl.add(studentLink);
			schl.add(teacherLink);
		}
		
		Link selfLink = linkTo(methodOn(this.getClass()).getSchools())
	                          .withSelfRel();
		return new CollectionModel<>(schools,selfLink);
	}
	
	@GetMapping("/schools/{id}")
	public school getSchoolById(@PathVariable int id)
	{
		logger.trace("Get school by Id called");
		school schl = schoolsrv.getSchoolById(id);
		schl.add(linkTo(methodOn(this.getClass()).getSchoolById(id)).withSelfRel());
		schl.add(linkTo(methodOn(this.getClass()).getTeachers(id)).withRel("Teachers"));
		schl.add(linkTo(methodOn(this.getClass()).getStudents(id)).withRel("Students"));
		return schl;
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
	public CollectionModel<Student> getStudents(@PathVariable int schoolId)
	{
		logger.trace("Get students by school Id - " + schoolId + " called");
		List<Student> students = studentsrv.getStudentsBySchId(schoolId);
		students.stream().forEach((s) -> s.add(linkTo(methodOn(this.getClass()).getStudentbyId(schoolId, s.getId()))
				                                .withSelfRel()));
		Link selfLink = linkTo(methodOn(this.getClass()).getStudents(schoolId)).withSelfRel();
		return new CollectionModel<>(students,selfLink) ;
	}
	
	
	@PostMapping("/schools/{schoolId}/students")
	public void addStudent(@RequestBody Student student, @PathVariable int schoolId) {
		logger.trace("Adding student to schoolId - " + schoolId);
		studentsrv.addStudent(student,schoolId);
		schoolsrv.updateStudentCount(schoolId,1);
		teachersrv.updateStudentCount(student.getTeacherId(),1);
		
	}
	
	@GetMapping("/schools/{schoolId}/students/{studentId}")
	public Student getStudentbyId(@PathVariable int schoolId, @PathVariable int studentId)
	{
		logger.trace("Get student by Id called");
		Student student = studentsrv.getStudentById(schoolId,studentId);
		student.add(linkTo(methodOn(this.getClass()).getStudentbyId(schoolId, studentId)).withSelfRel());
		return studentsrv.getStudentById(schoolId,studentId);
	}

	@GetMapping("/schools/{schoolId}/teachers")
    public CollectionModel<Teacher> getTeachers(@PathVariable int schoolId)
    {
		logger.trace("Get teachers by school Id - " + schoolId + " called");
		List<Teacher> teachers = teachersrv.getTeachersBySchlId(schoolId);
		teachers.stream().forEach(s -> s.add(linkTo(methodOn(this.getClass()).getTeacherbyId(schoolId,s.getId())).withSelfRel()));
		Link selfLink = linkTo(methodOn(this.getClass()).getTeachers(schoolId)).withRel("teacher");
		return new CollectionModel<>(teachers,selfLink);
    }
	
	@GetMapping("/schools/{schoolId}/teachers/{teacherId}")
	public Teacher getTeacherbyId(@PathVariable int schoolId, @PathVariable int teacherId)
	{
		logger.trace("Get teacher by Id called");
		Teacher teacher = teachersrv.getTeacherById(schoolId,teacherId);
		teacher.add(linkTo(methodOn(this.getClass()).getTeacherbyId(schoolId, teacherId)).withSelfRel());
		return teachersrv.getTeacherById(schoolId,teacherId);
	}


    @PostMapping("/schools/{schoolId}/teachers")
    public void addTeacher(@RequestBody Teacher teacher, @PathVariable int schoolId)
    {
    	logger.trace("Adding teacher to schoolId - " + schoolId);
    	teachersrv.addTeacher(teacher,schoolId);
    	schoolsrv.updateTeacherCount(schoolId,1);
    }
}
