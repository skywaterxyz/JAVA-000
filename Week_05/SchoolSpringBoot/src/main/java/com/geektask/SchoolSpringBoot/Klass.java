package com.geektask.SchoolSpringBoot;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Klass { 
    
    List<Student> students;
    
    public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void dong(){
        System.out.println(this.getStudents());
    }
    
    public List<Student> getStudents() {
    	return this.students;
    }
}
