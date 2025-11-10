package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.AttendeeDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Service
public class AttendeeService {
  @Autowired
  private AttendeeDao attendeeDao;
//ATTENDEE
//Register Attendee
public ResponseEntity<ResponseStructure<Attendee>> registerAttendee(Attendee attendee){
	ResponseStructure<Attendee> response=new ResponseStructure<Attendee>();
	response.setStatusCode(HttpStatus.CREATED.value());
	response.setMessage("Attendee is Registerd");
	response.setData(attendeeDao.registerAttendee(attendee));
	return new ResponseEntity<ResponseStructure<Attendee>>(response,HttpStatus.CREATED);
}
//Get all attendee
public ResponseEntity<ResponseStructure<List<Attendee>>> getAllAttendee(){
		List<Attendee> attendee = attendeeDao.getAllAttendee() ;
		ResponseStructure<List<Attendee>> response = new ResponseStructure<List<Attendee>>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("The Attendee Details is Retrieved");
			response.setData(attendee);
			return new ResponseEntity<ResponseStructure<List<Attendee>>>(response, HttpStatus.OK);
	}
//Get Attendee By Id
public ResponseEntity<ResponseStructure<Optional<Attendee>>> getAttendeeById(Integer id){
	Optional<Attendee> opt=attendeeDao.getAttendeeById(id);
	if(opt.isPresent()) {
		ResponseStructure<Optional<Attendee>> response=new ResponseStructure<Optional<Attendee>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Attendee is retrived by id :"+id);
		response.setData(opt);
		return new ResponseEntity<ResponseStructure<Optional<Attendee>>>(response, HttpStatus.OK);
	}else
	throw new IdNotFoundException("No attendee is found in DB");
}
//Update Attendee
public ResponseEntity<ResponseStructure<Attendee>> updateAttendee(Attendee attendee){
		ResponseStructure<Attendee> response=new ResponseStructure<Attendee>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Attendee is updated Sucessfully");
		response.setData(attendeeDao.updateAttendee(attendee));
		return new ResponseEntity<ResponseStructure<Attendee>>(response, HttpStatus.OK);
}
//Delete Attendee
public  ResponseEntity< ResponseStructure<String>> deleteAttendee(Integer id){
	ResponseStructure<String> response=new ResponseStructure<String>();
	attendeeDao.deleteAttendee(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Attendee is Deleted");
		response.setData("Attendee id Deleted");
		return new  ResponseEntity< ResponseStructure<String>>(response,HttpStatus.OK);
}
//Get Attendee By Contact
public ResponseEntity<ResponseStructure<Optional<Attendee>>> getAttendeeByContact(Long contact){
	Optional<Attendee> attendee=attendeeDao.getAttendeeByContact(contact);
	ResponseStructure<Optional<Attendee>> response=new ResponseStructure<Optional<Attendee>>();
	if(!attendee.isEmpty()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Attendee is Getted based in Contact");
		response.setData(attendee);
		return new ResponseEntity<ResponseStructure<Optional<Attendee>>>(response, HttpStatus.OK);
	}
	else {
		throw new NoRecordAvailableException("No Attendee is Present");
	}
}
//Pagination and Sorting
public ResponseEntity<ResponseStructure<Page<Attendee>>> getAttendeeByPaginationAndSorting(int pageNumber,int pageSize,String field){
		ResponseStructure<Page<Attendee>> response = new ResponseStructure<Page<Attendee>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Attendee sorted based on pagination");
		response.setData(attendeeDao.getAttendeeByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Attendee>>>(response,HttpStatus.OK);
	}
}
