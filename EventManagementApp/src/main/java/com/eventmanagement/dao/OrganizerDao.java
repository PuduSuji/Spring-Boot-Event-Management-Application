package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Repository.OrganizerRepository;
import com.eventmanagement.entity.Organizer;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;
@Repository
public class OrganizerDao {
	@Autowired
	private OrganizerRepository organizerRepository;
	//ORGANIZER
		//Add Organizer
		public Organizer addOrganizer(Organizer organizer) {
			return organizerRepository.save(organizer);
		}
		//Get All Organizer
		public List<Organizer> getAllOrganizer(){
			return organizerRepository.findAll();
		}
		//Get Organizer By ID
		public Optional<Organizer> getOrganizerById(int id){
			return organizerRepository.findById(id);
		}
		//Update the Organizer
		public Organizer updateOrganizer(Organizer organizer) {
			Optional<Organizer> opt =organizerRepository.findById(organizer.getId());
			if(opt.isPresent())
			    return organizerRepository.save(organizer);
			else
			throw new IdNotFoundException("Id is not available in DB");
		}
		//Delete Organizer
		public void deleteOrganizer(Integer id) {
			Optional<Organizer> opt = organizerRepository.findById(id);
			if(opt.isPresent()) {
			     organizerRepository.delete(opt.get());
			}else {
			throw new IdNotFoundException("Id is not available in DB");
			}
		}
		//Pagination and Sorting
		public Page<Organizer> getOrganizerByPaginationAndSorting(int pageNumber,int pageSize,String field){
			Page<Organizer> pages= organizerRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).descending()));
			if(!pages.isEmpty())
				return pages;
			else
			throw new NoRecordAvailableException("No Organizer is available");

		}
		
}
