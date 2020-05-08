package com.district.schools;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface schoolDB extends CrudRepository<school, Integer> {

	public school findByName(String name);
}
