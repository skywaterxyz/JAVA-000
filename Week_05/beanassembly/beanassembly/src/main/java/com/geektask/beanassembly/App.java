package com.geektask.beanassembly;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	BeanService beanService = (BeanService) context.getBean("beanService");
    	beanService.testSchoolBean();
    	beanService.testTeacher1();
    	beanService.testBossTeacher();
    	beanService.testAssistTeacher();
    }
}
