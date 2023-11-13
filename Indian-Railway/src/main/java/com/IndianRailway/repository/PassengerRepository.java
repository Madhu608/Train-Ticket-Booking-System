package com.IndianRailway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IndianRailway.entities.MyBooking;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.User;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    //pagination
	
	
//	 @Query(" from Passenger as c where c.user.id =:userId")
//	 //currentPage-Page
//	 //Passenger per Page-5
//   public  Page<Passenger>findPassengerByEmailId(@Param("userId") Long userName, Pageable pageable);

	
	 @Query(" from Passenger as c where c.user.id =:userId")
      public  List<Passenger>findPassengerByEmailId(@Param("userId") Long userName);
	

	  Passenger findById(long id);

	  
	  @Query("select P from Passenger P where P.user.id =:userId and P.id=:id")
      public  List<Passenger>findPassengersByEmailId(@Param("userId") Long userId,@Param("id") Long id);

	 
//	  @Query("select u from Passenger u where u.id =:id")
//      public Passenger findPassengerById(@Param("id") Long id);
	 
	 
//	 @Query("select u from Passenger u where u.email =:email")
//     public Passenger findByEmailId(@Param("email") String email);
	  
	  

}
