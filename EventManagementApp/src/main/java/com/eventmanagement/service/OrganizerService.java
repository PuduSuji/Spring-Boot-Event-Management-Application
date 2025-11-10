package com.eventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eventmanagement.dao.OrganizerDao;
import com.eventmanagement.dto.ResponseStructure;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.exception.IdNotFoundException;

@Service
public class OrganizerService {
  @Autowired
  private OrganizerDao organizerDao;
//ORGANIZER
		//Add Organizer
	 public ResponseEntity<ResponseStructure<Organizer>> addOrganizer(Organizer organizer){
	ResponseStructure<Organizer> response=new ResponseStructure<Organizer>();
	response.setStatusCode(HttpStatus.CREATED.value());
	response.setMessage("Organizer is Assined");
	response.setData(organizerDao.addOrganizer(organizer));
	return new ResponseEntity<ResponseStructure<Organizer>>(response,HttpStatus.CREATED);
}
	 //Get All organizer
	 public ResponseEntity<ResponseStructure<List<Organizer>>> getAllOrganizer(){
	List<Organizer> organizer = organizerDao.getAllOrganizer() ;
	ResponseStructure<List<Organizer>> response = new ResponseStructure<List<Organizer>>();
	
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("The Organizer Details is Retrieved");
		response.setData(organizer);
		
		return new ResponseEntity<ResponseStructure<List<Organizer>>>(response, HttpStatus.OK);
}
	 //Get Organizer By ID
	public  ResponseEntity<ResponseStructure<Optional<Organizer>>> getOrganizerById(int id){
	Optional<Organizer> opt = organizerDao.getOrganizerById(id);
	ResponseStructure<Optional<Organizer>> response = new ResponseStructure<Optional<Organizer>>();
	if (opt.isPresent()) {
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Organizer get By id");
		response.setData(opt);
		return new  ResponseEntity<ResponseStructure<Optional<Organizer>>>(response,HttpStatus.OK);
	} else {
		throw new IdNotFoundException("Id is not available in DB");
	}
}
	//Update Organizer
public ResponseEntity<ResponseStructure<Organizer>> updateOrganizer(Organizer organizer){
	ResponseStructure<Organizer> response = new ResponseStructure<Organizer>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(" Organizer details is Updated");
		response.setData(organizerDao.updateOrganizer(organizer));
		return new ResponseEntity<ResponseStructure<Organizer>>(response,HttpStatus.OK);
}
//Delete Organizer
public  ResponseEntity< ResponseStructure<String>> deleteOrganizer(Integer id){
	ResponseStructure<String> response=new ResponseStructure<String>();
	organizerDao.deleteOrganizer(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Organizer is Deleted");
		response.setData("Organizer id Deleted");
		return new  ResponseEntity< ResponseStructure<String>>(response,HttpStatus.OK);
}
//Pagination and Sorting
public ResponseEntity<ResponseStructure<Page<Organizer>>> getOrganizereByPaginationAndSorting(int pageNumber,int pageSize,String field){
		ResponseStructure<Page<Organizer>> response = new ResponseStructure<Page<Organizer>>();
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Organizer sorted based onn pagination");
		response.setData(organizerDao.getOrganizerByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Organizer>>>(response,HttpStatus.OK);
	}

}
