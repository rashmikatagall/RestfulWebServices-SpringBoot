package com.district.schools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.district.address.addressDB;

@Service
public class schoolService {

	@Autowired
	private schoolDB schoolDb;
	
	@Autowired
	private addressDB addressDb;
	
	Logger logger = LoggerFactory.getLogger(schoolService.class);
	
 	public List<school> getAllSchools() {
		logger.trace("getAllSchools >> Fetching all schools");
		
		List<school> schools = new ArrayList<>();
		
		Iterator<school> it = schoolDb.findAll().iterator();
		while(it.hasNext())
			schools.add(it.next());

		logger.trace("getAllSchools << " + schools.size() + "schools fecthed");
		return schools;
	}

	public school getSchoolById(int id) {
		logger.trace("getSchoolById >> schoolId - " + id);
		school schl = schoolDb.findById(id).get();
		logger.trace("getSchoolById << schl fecthed " + schl.getName());
		return schl;
	}

	public void addSchool(school schl) throws Exception {
		logger.trace("addSchool >> schl - " + schl.getName());
		if (schoolDb.findByName(schl.getName())==null) {
		  addressDb.save(schl.getAddress());
		  schoolDb.save(schl);
		  logger.trace("addSchool << schl added ");
		  }
		else
		{
			logger.trace("addSchool << school exists");
			throw new Exception("School already exists!");
		}
	}

	public void updateStudentCount(int schoolId, int i) {
		logger.trace("updateStudentCount >> increasing student count by "+i);
		school schl = schoolDb.findById(schoolId).get();
		schl.setStudents(schl.getStudents()+i);
		schoolDb.save(schl);
		logger.trace("updateStudentCount << student count updated for " + schl.getName());
	}
	
	

}
