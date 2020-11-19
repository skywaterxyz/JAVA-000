package com.geektask.TestStarter;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geektask.SchoolSpringBoot.SchoolService;
import com.geektask.SchoolSpringBoot.Student;

@RestController
public class TestController {

	@Resource(name = "schoolService")
	private SchoolService schoolService;
	
	@Resource(name = "student")
	private Student student;
	
	@RequestMapping("/schoolname")
	public String getSchoolName() {
		return schoolService.getSchoolName();
	}
	
	@RequestMapping("/student")
	public Student getStudent() {
		return student;
	}
}
