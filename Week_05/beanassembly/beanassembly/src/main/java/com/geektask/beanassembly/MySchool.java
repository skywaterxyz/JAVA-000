package com.geektask.beanassembly;

import org.springframework.stereotype.Component;

@Component
public class MySchool implements ISchool {

	public String getSchoolName() {
		return "中南大学";
	}

}
