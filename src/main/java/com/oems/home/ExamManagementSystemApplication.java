package com.oems.home;

import com.oems.home.dao.Dao;
import com.oems.home.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamManagementSystemApplication {

	private static Dao<Student> dao;

	public ExamManagementSystemApplication(Dao<Student> dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(ExamManagementSystemApplication.class, args);
	}

}
