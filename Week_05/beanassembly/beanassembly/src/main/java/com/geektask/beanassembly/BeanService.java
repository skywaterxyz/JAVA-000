package com.geektask.beanassembly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BeanService {

	@Autowired
	ISchool school;
	
	@Resource(name = "teacher1")
	Teacher itTeacheer;
	
	@Resource(name = "teacher2")
	Teacher bossTeacheer;
	
	@Resource(name = "teacher3")
	Teacher assistTeacher;
	
	public void testSchoolBean() {
		System.out.println(school.getSchoolName());
	}

	
	public void testTeacher1() {
		System.out.println(itTeacheer.toString());
	}
	
	public void testBossTeacher() {
		System.out.println(bossTeacheer.toString());
	}
	
	public void testAssistTeacher() {
		System.out.println(assistTeacher.toString());
	}
}
