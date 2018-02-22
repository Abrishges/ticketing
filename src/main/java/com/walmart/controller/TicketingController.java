package com.walmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.dto.AvailableSeats;
import com.walmart.dto.SeatHold;
import com.walmart.services.AvailableSeatsService;
import com.walmart.services.SeatHoldService;
import com.walmart.services.TicketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TicketingController {

  private final TicketService ticketService;
  private final AvailableSeatsService availableSeatsService;
  private final SeatHoldService seatHoldService;

  public static final String BASE_PATH = "/tickets";
  public static final String ID_PATH_NUM_SEATS = "/numofseats";
  public static final String ID_PATH_AVAIL_SEATS = "/availableSeats";
  public static final String HOLD_BEST_AVAIL_SEATS = "/holdBestAvailableSeats";
  public static final String RESERVE_SEATS = "/reserveSeats";

  @Autowired
  public TicketingController(
      final TicketService ticketService,
      final AvailableSeatsService availableSeatsService,
      final SeatHoldService seatHoldService) {
    this.ticketService = ticketService;
    this.availableSeatsService = availableSeatsService;
    this.seatHoldService = seatHoldService;
  }

  @ApiOperation(value = "Ticket ", notes = "Ticket .....")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Fields are with validation errors")})
  @CrossOrigin(origins = "*")
  @RequestMapping(value = BASE_PATH + ID_PATH_NUM_SEATS, method = RequestMethod.GET)
  public int numSeatsAvailable() {
    return this.ticketService.numSeatsAvailable();
  }

  @ApiOperation(value = "Ticket ", notes = "Ticket .....")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Fields are with validation errors")})
  @CrossOrigin(origins = "*")
  @RequestMapping(value = BASE_PATH + ID_PATH_AVAIL_SEATS, method = RequestMethod.GET)
  public List<AvailableSeats> findAllAvailableSeats() {
    return this.availableSeatsService.findAllAvailableSeats();
  }

  @ApiOperation(value = "Ticket ", notes = "Ticket .....")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Fields are with validation errors")})
  @CrossOrigin(origins = "*")
  @RequestMapping(value = BASE_PATH + HOLD_BEST_AVAIL_SEATS, method = RequestMethod.GET)
  public SeatHold findAndHoldSeats(final int numSeats, final String customerEmail) {
    return this.ticketService.findAndHoldSeats(numSeats, customerEmail);
  }

  @ApiOperation(value = "Ticket ", notes = "Ticket .....")
  @ApiResponses(value = {@ApiResponse(code = 400, message = "Fields are with validation errors")})
  @CrossOrigin(origins = "*")
  @RequestMapping(value = BASE_PATH + RESERVE_SEATS, method = RequestMethod.GET)
  public String reserveSeats(final int seatHoldId, final String customerEmail) {
    return this.ticketService.reserveSeats(seatHoldId, customerEmail);
  }
}
