package com.greatlearning.tickettracker.controller;

import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;  

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.tickettracker.model.Ticket;
import com.greatlearning.tickettracker.service.Ticketservice;

@Controller
public class TicketController {

	@Autowired
	Ticketservice service;
	
	@GetMapping("/tickettracker")
	public String getAllTickets(Model model) {
		List<Ticket> result = service.getAllTickets();
		model.addAttribute("tickets", result);
		return "ticket";
	}

	@GetMapping("/tickettracker/new")
	public String addNewTicket(Model model) {
		Ticket tic = new Ticket();
//		Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());;
		model.addAttribute("ticket", tic);
		return "create_ticket";
	}

	@GetMapping("/tickettracker/edit/{id}")
	public String updateTicket(Model model, @PathVariable("id") int id) {
		Ticket tic = service.getTicketById(id);
		model.addAttribute("ticket", tic);
		return "edit_ticket";
	}

	@GetMapping("/tickettracker/delete/{id}")
	public String deleteTicket(@PathVariable("id") int id) {
		service.deleteTicketById(id);
		return "redirect:/tickettracker";
	}
	
	@GetMapping("/tickettracker/view/{id}")
	public String viewTicket(Model model,@PathVariable("id") int id){
		Ticket existingtic = service.getTicketById(id);
		model.addAttribute("ticket", existingtic);
		return "view_ticket";
	}
	
	@RequestMapping(path = "/tickettracker/search")
	public String search(Ticket ticket,String keyword,Model model) {
		if(keyword!=null) {
			List<Ticket> list = service.search(keyword);
			model.addAttribute("Tickets", list);
			return "ticket";
		}
		return "redirect:/tickettracker";
	}

	@PostMapping("/tickettracker")
	public String addEmployees(@ModelAttribute(name = "ticket") Ticket tic) {
		Date d1 = new Date();
		tic.setDate(d1);
		service.saveOrUpdate(tic);
		return "redirect:/tickettracker";
	}

	@PostMapping("/tickettracker/{id}")
	public String updateEmployees(@ModelAttribute(name = "ticket") Ticket tic, @PathVariable
			("id") int id) {
		Ticket existingtic = service.getTicketById(id);
		if (existingtic.getId() == tic.getId()) {
			existingtic.setTitle(tic.getTitle());
			existingtic.setDesc(tic.getDesc());
			existingtic.setContent(tic.getContent());
			service.saveOrUpdate(existingtic);
		}
		return "redirect:/tickettracker";
	}
}
