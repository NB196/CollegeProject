package com.lastmin.LastMin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	//find available appointments
		List<Appointment> findByBookedInd(String bookedInd);
	
	//custom query to find appointment by user id
	Appointment findAppByUserId(int userId);
	
	//find all appointments
	List<Appointment> findAll();
	
	
	

}
