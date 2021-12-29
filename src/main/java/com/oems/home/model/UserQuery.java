package com.oems.home.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuery extends JpaRepository<BaseUser, String>{
	BaseUser findByNID(String nID);
}
