package com.greatlearning.tickettracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.tickettracker.TicketRepo.TicketRepository;
import com.greatlearning.tickettracker.model.Ticket;

@Service
public class TicketserviceImpl implements Ticketservice{

	@Autowired
	TicketRepository repo;
	
	@Override
	public List<Ticket> getAllTickets() {
		return repo.findAll();
	}

	@Override
	public void saveOrUpdate(Ticket tic) {
		repo.save(tic);
	}

	@Override
	public void deleteTicketById(int id) {
		Ticket tic = getTicketById(id);
		repo.delete(tic);
	}

	@Override
	public Ticket getTicketById(int id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Ticket> search(String keyword) {
		return repo.findByKeyword(keyword);
	}

	

}
