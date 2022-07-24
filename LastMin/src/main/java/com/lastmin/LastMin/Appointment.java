package com.lastmin.LastMin;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "appointment")
@EntityListeners(AuditingEntityListener.class)


public class Appointment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "APPOINTMENTID", nullable = false, unique = true)
	private int appointmentId;
	
	
	@Column(name = "USERID", nullable = false, unique = true)  
	private int userId;
	  
	@Column(name = "TREATMENTTYPE", nullable = false, unique = false)  
	private String treatmentType;
	
	@Column(name = "DATE", nullable = false, unique = false)  
	private String date;
	
	@Column(name = "TIME", nullable = false, unique = false)  
	private String time;
	
	//constructor
	public Appointment() {
		super();
	}
	
	
	public Appointment(int appointmentId, int userId, String treatmentType, String date, String time) {
		super();
		this.appointmentId = appointmentId;
		this.userId = userId;
		this.treatmentType = treatmentType;
		this.date = date;
		this.time = time;
	}
	

	//getters and setters

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTreatmentType() {
		return treatmentType;
	}

	public void setTreatmentType(String treatmentType) {
		this.treatmentType = treatmentType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appointmentId, date, time, treatmentType, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		return appointmentId == other.appointmentId && Objects.equals(date, other.date)
				&& Objects.equals(time, other.time) && Objects.equals(treatmentType, other.treatmentType)
				&& userId == other.userId;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", userId=" + userId + ", treatmentType=" + treatmentType
				+ ", date=" + date + ", time=" + time + "]";
	}
	
	
	
	
	

}
