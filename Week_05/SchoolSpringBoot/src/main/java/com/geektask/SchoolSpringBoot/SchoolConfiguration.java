package com.geektask.SchoolSpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchoolConfiguration {

	@Bean(name = "student")
	public Student getStudent() {
		return new Student();
	}
	
	@Bean(name = "schoolService")
	public SchoolService getSchoolService() {
		return new SchoolService();
	}

}
