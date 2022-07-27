package com.lastmin.LastMin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	//get all appointments- booked and available
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

	//beauticians can delete the appointments they created
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
	
	//modify the time of appointment by using appointment id
	@PutMapping("modify/time/{id}")
	public ResponseEntity<Appointment> modifyTime(@PathVariable("id") int id, @RequestBody Appointment appointment){
		Optional<Appointment> appData = repo.findById(id);
		
		if(appData.isPresent()) {
			Appointment app = appData.get();
			app.setTime(appointment.getTime());
			return new ResponseEntity<>(repo.save(app), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//modify the date of appointment by using appointment id
		@PutMapping("modify/date/{id}")
		public ResponseEntity<Appointment> modifyDate(@PathVariable("id") int id, @RequestBody Appointment appointment){
			Optional<Appointment> appData = repo.findById(id);
			
			if(appData.isPresent()) {
				Appointment app = appData.get();
				app.setDate(appointment.getDate());
				return new ResponseEntity<>(repo.save(app), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		//client cancel app by updating bookedInd by using appointment id
		@PutMapping("modify/cancel/{id}")
		public ResponseEntity<Appointment> updateBookedInd(@PathVariable("id") int id, @RequestBody Appointment appointment){
			Optional<Appointment> appData = repo.findById(id);
			
			if(appData.isPresent()) {
				Appointment app = appData.get();
				app.setBookedInd(appointment.getBookedInd());
				return new ResponseEntity<>(repo.save(app), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		//find all available apps (not booked by clients)
		@GetMapping("/available")
		public ResponseEntity<List<Appointment>> findAvailableApps(){
			try {
				List<Appointment> appointment = repo.findByBookedInd("N");
				if(appointment.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(appointment, HttpStatus.OK);
			}catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// find all apps booked by clients
		@GetMapping("/bookings")
		public ResponseEntity<List<Appointment>> findBookedApps() {
			try {
				List<Appointment> appointment = repo.findByBookedInd("Y");
				if (appointment.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(appointment, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
		//book appointment by app id, by updating the bookingInd to Y
		@PutMapping("modify/book/{id}")
		public ResponseEntity<Appointment> bookApp(@PathVariable("id") int id, @RequestBody Appointment appointment){
			//List<Appointment> app1 = repo.findByBookedInd("N");
			Optional<Appointment> appData = repo.findById(id);
			
			if(appData.isPresent()) {
				Appointment app = appData.get();
				app.setBookedInd(appointment.getBookedInd());
				return new ResponseEntity<>(repo.save(app), HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	
}
