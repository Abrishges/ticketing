package com.walmart.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.dto.AvailableSeats;
import com.walmart.dto.SeatHold;
import com.walmart.util.EmailValidator;

@Service
public class TicketServiceImpl implements TicketService {

  private static final Logger LOG = LoggerFactory.getLogger(TicketServiceImpl.class);
  private final AvailableSeatsService availableSeatsService;
  private final SeatHoldService seatHoldService;
  private final EmailValidator emailValidator;

  private final int SEAT_RELEASE_TIME = 3;

  @Autowired
  public TicketServiceImpl(
      final AvailableSeatsService availableSeatsService,
      final SeatHoldService seatHoldService,
      final EmailValidator emailValidator) {
    this.availableSeatsService = availableSeatsService;
    this.seatHoldService = seatHoldService;
    this.emailValidator = emailValidator;
  }

  // In perfect design we need to pass eventId, and return number of available seats for that event
  @Override
  public int numSeatsAvailable() {
    final List<AvailableSeats> availableSeats = this.availableSeatsService.findAllAvailableSeats();
    return availableSeats.size();
  }

  @Override
  public SeatHold findAndHoldSeats(final int numSeats, final String customerEmail) {

    if (!this.emailValidator.isValidEmailAddress(customerEmail)) {
      throw new IllegalArgumentException("The customer Email not valid");
    }

    final List<AvailableSeats> bestAvailableSeats = this.availableSeatsService.findBestAvailableSeats(numSeats);
    final SeatHold seatHold = new SeatHold();
    seatHold.setCustomerEmail(customerEmail);
    seatHold.setAvailableSeats(bestAvailableSeats);
    seatHold.setReleaseDatetime(this.getNewTime().toString());

    // Populate seats hold object
    final SeatHold seatHoldRespose = this.seatHoldService.holdSeats(seatHold);
    // we should also change the status of the seats reserved to true
    this.availableSeatsService.seatHold(bestAvailableSeats);
    return seatHoldRespose;
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {
    if (!this.emailValidator.isValidEmailAddress(customerEmail)) {
      throw new IllegalArgumentException("The customer Email not valid");
    }
    return this.seatHoldService.reserveSeats(seatHoldId, customerEmail);
  }

  // create release date time, this method can be moved to a separate class
  public LocalDateTime getNewTime() {
    // Get time after 3 minutes
    final Duration duration = Duration.ofMinutes(this.SEAT_RELEASE_TIME);
    final LocalDateTime newTime = LocalDateTime.now().plus(duration);
    LOG.info("seatHoldId: [{}]", newTime);
    return newTime;
  }
}
