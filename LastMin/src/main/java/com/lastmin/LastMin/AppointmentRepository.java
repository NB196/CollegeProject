package com.lastmin.LastMin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	//add in all methods here
	
	//custom query to find appointment by user id
	Appointment findAppByUserId(int userId);
	
	//find all appointments
	List<Appointment> findAll();
	
	//custom query to find all booked appointments
	//List<Appointment> findBookedApps(boolean bookedInd);
	
	//create app
	//int addApp (Appointment a);
	
	//save app
	//Appointment save (Appointment app);

	//void saveAll(Appointment a);

}
