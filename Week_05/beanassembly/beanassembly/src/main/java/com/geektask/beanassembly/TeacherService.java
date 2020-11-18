package com.geektask.beanassembly;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
	
	@Bean(name="teacher2")
	public Teacher getTeacher2() {
		Teacher t2 = new Teacher();
		t2.setName("caiz");
		t2.setSalary(20000);
		return t2;
	}
	
	@Bean
	public Teacher teacher3() {
		Teacher t = new Teacher();
		t.setName("assist");
		t.setSalary(100);
		return t;
	}
}
