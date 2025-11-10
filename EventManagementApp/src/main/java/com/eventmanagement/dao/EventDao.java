package com.eventmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.eventmanagement.Repository.EventRepository;
import com.eventmanagement.entity.Attendee;
import com.eventmanagement.entity.Event;
import com.eventmanagement.exception.IdNotFoundException;
import com.eventmanagement.exception.NoRecordAvailableException;

@Repository
public class EventDao {
	@Autowired
	private EventRepository eventRepository;
	//EVENT
		//Create Event
		public Event createEvent(Event event) {
			return eventRepository.save(event);
		}
		//Get All Events
		public List<Event> getAllEvent(){
			return eventRepository.findAll();
		}
		//Get Event By Id
		public Optional<Event> getEventById(Integer id) {
			return eventRepository.findById(id);
		}
		//Update event
		public Event updateEvent(Event event) {
			Optional<Event> opt =eventRepository.findById(event.getId());
			if(opt.isPresent())
			    return eventRepository.save(event);
			else
			throw new IdNotFoundException("Id is not available in DB");
		}
	    //Delete Event
		public void deleteEvent(Integer id) {
			Optional<Event> opt = eventRepository.findById(id);
			if(opt.isPresent()) {
			     eventRepository.delete(opt.get());
			}else {
			throw new IdNotFoundException("Id is not available in DB");
			}
		}
		//get Attendee by event Id
		public List<Attendee> getAttendeeByEventId(Integer id) {
			return eventRepository.findAttendeeByEventId(id);
		}

		//Get Event By Organizer Id
		public Optional<List<Event>> getEventByOrganizerId(Integer id){
			return eventRepository.findEventByOrganizerId(id);
		}


		//Pagination and Sorting
			public Page<Event> getEventByPaginationAndSorting(int pageNumber,int pageSize,String field){
				Page<Event> pages= eventRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).descending()));
				if(!pages.isEmpty())
					return pages;
				else
				throw new NoRecordAvailableException("No Event is available");

			}
}
