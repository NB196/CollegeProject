package com.lastmin.LastMin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerErrorException;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	// AppointmentRepository appRepo;
	AppointmentRepository repo;

	//get all appointments
	@GetMapping("/all")
	public List<Appointment> index() {
		// return appRepo.findAll();
		return repo.findAll();
	}

	//get specific appointment by id
	@GetMapping("/filter/{id}")
	public Appointment getAppById(@PathVariable int id) {
		Appointment app = null;
		try {
			app = repo.findAppByUserId(id);
		} catch (Exception e) {
			throw new ServerErrorException("Backend issue", e);
		}
		if (app == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No appointment with" + id + "id was found");
		}
		return app;
	}

	//Cancel appointment by userId
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable String id) {
		int userId = Integer.parseInt(id);
		repo.deleteById(userId);
		return true;
	}

	//beauticians create their appointment- they can input their own details
	@PostMapping("/create")
	public ResponseEntity<Appointment> createApp(@RequestBody Appointment appointment) {
		try {
			Appointment app = repo.save(new Appointment(appointment.getUserId(), appointment.getTreatmentType(),
					appointment.getDate(), appointment.getCost(), appointment.getTime(), appointment.getBookedInd()));
			return new ResponseEntity<>(app, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
