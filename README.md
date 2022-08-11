# CollegeProject


# Run the Application
1. Go to src/main/java 
2. Go into com.lastmin.LastMin
3. Go into file: LastMinApplication.java
4. Right click on class, and run as : Spring Boot Application


# Database Credentials

spring.datasource.url=jdbc:mysql://localhost:3306/lastmin
spring.datasource.username=root
spring.datasource.password=password1

# Database DDL and Insert Data

DROP TABLE appointment IF EXISTS

CREATE TABLE appointment
	(APPOINTMENTID integer,
	USERID integer,
	TREATMENTTYPE VARCHAR (40),
	DATE VARCHAR (10),
	COST integer (100),
	TIME VARCHAR (5),
	BOOKEDIND VARCHAR(1)
);

INSERT INTO appointment (APPOINTMENTID,USERID,TREATMENTTYPE,DATE,COST,TIME, BOOKEDIND) VALUES (10,2, 'shellac on toes', '21-08-2022', 45, '15:00', 'Y');
INSERT INTO appointment (APPOINTMENTID,USERID,TREATMENTTYPE,DATE,COST,TIME, BOOKEDIND) VALUES (11,3, 'eyebrow tint and threading', '15-08-2022',35,'10:00', 'Y');
INSERT INTO appointment (APPOINTMENTID,USERID,TREATMENTTYPE,DATE,COST,TIME, BOOKEDIND) VALUES (12,5, 'pedicure', '16-08-2022',40,'15:00', 'Y');

