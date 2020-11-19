package com.geektask.SchoolSpringBoot;

import java.util.Arrays;
import java.util.List;

public class SchoolService {
	
	public List<String> getTeachers() {
		return Arrays.asList("蔡老师", "唐老师");
	}
	
	public String getSchoolName() {
		return "中南大学";
	}
}
