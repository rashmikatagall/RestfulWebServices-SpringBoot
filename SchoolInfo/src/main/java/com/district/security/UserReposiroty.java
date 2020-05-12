package com.district.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface UserReposiroty extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
