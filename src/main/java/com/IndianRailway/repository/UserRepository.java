package com.IndianRailway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {


	boolean existsByEmailId(String emailId);
//
//	void findByEmailId(String emailId);
	
	
    @Query("select u from User u where u.emailId =:emailId")
      public User findByEmailId(@Param("emailId") String emailId);


	
//	public User findByEmailId(String emailId);
}
