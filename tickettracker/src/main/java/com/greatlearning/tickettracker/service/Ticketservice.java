package com.greatlearning.tickettracker.service;

import java.util.List;

import com.greatlearning.tickettracker.model.Ticket;

public interface Ticketservice {

	public List<Ticket> getAllTickets();
	public void saveOrUpdate(Ticket tic);
	public void deleteTicketById(int id);
	public Ticket getTicketById(int id);
	public List<Ticket> search(String keyword);
}
