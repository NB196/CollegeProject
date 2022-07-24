package com.lastmin.LastMin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	//query to find appointment by user id
	Appointment findAppByUserId(int userId);

}
