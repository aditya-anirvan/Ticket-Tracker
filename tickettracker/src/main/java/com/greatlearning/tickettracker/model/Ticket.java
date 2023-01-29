package com.greatlearning.tickettracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="ticket")
@Data
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticid")
	private int id;
	@Column(name = "tictitle")
	private String title;
	@Column(name = "ticdesc")
	private String desc;
	@Column(name = "ticket_content")
	private String content;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date")
    private Date date;
}
