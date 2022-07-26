package com.lastmin.LastMin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;


@RestController
public class AppointmentController {
	
	@Autowired
	//AppointmentRepository appRepo;
	AppointmentRepository repo;
	
	//url to get all appointments
	@GetMapping("/appointments")
	public List<Appointment> index(){
		//return appRepo.findAll();
		return repo.findAll();
	}
		
	//url to get appointment by id
	@GetMapping("appointments/{id}")
	public Appointment getAppById(@PathVariable int id){
		Appointment app = null;
		try {
			app = repo.findAppByUserId(id);
		}catch(Exception e) {
			throw new ServerErrorException("Backend issue", e);
		}
		if(app == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No appointment with" +id+ "id was found");
		}
		return app;
	}
	
	//Cancel appointment by userId- this works	 
	@DeleteMapping("appointments/{id}")
	public boolean delete(@PathVariable String id) {
	int userId = Integer.parseInt(id);
	repo.deleteById(userId);
	return true;
	 }
}
	
	
		



