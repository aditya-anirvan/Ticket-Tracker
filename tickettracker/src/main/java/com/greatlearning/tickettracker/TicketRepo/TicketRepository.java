package com.greatlearning.tickettracker.TicketRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greatlearning.tickettracker.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>{

//	List<Ticket> findByTitleContainsAndDescContainsAllIgnoreCase(String tictitle, String ticdesc);
	@Query(value = "select * from ticket t where t.tictitle like %:keyword% or t.ticdesc like %:keyword%", nativeQuery = true)
	 List<Ticket> findByKeyword(@Param("keyword") String keyword);
}
