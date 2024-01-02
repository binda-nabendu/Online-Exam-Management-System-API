package com.oems.home;

import com.oems.home.dao.Dao;
import com.oems.home.model.Student;
import com.oems.home.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExamManagementSystemApplication extends SpringBootServletInitializer {

	private static Dao<Student> studentDao;
	private static Dao<Teacher> teacherDao;

	public ExamManagementSystemApplication(Dao<Student> studentDao, Dao<Teacher> teacherDao) {
		this.studentDao = studentDao;
		ExamManagementSystemApplication.teacherDao = teacherDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamManagementSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
		return builder.sources(ExamManagementSystemApplication.class);
	}

}
