package com.IndianRailway.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IndianRailway.entities.MyBooking;
import com.IndianRailway.entities.Passenger;
import com.IndianRailway.entities.User;

public interface MyBookingRepository extends JpaRepository<MyBooking, Long> {
	
	public MyBooking findByBookingId(String bookingId);
	
	
	@Query(" from MyBooking as c where c.user.id =:userId")
     public   List<MyBooking>findMyBookingByPaymentId(@Param("userId") Long userId);



	//
//	  @Query("select u from MyBooking u where u.paymentId =:paymentId")
//      public User findByPaymentId(@Param("paymentId") String paymentId);


}
