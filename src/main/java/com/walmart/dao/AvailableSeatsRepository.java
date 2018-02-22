package com.walmart.dao;

import java.util.List;

import com.walmart.dto.AvailableSeats;

public interface AvailableSeatsRepository {

  public List<AvailableSeats> findAllAvailableSeats();

  public void deleteAvailableSeats(String eventId, String sectionId, String rowId, String seatNum);

  public void reserveSeats(List<AvailableSeats> availableSeats);

  public AvailableSeats editAvailableSeat(AvailableSeats availableSeats);

  public void seatHold(final List<AvailableSeats> bestAvailableSeats);

  // return all available seats, seats which are available
  // return best available seats ( this can be moved to service layer of available seats)
  // change reserve status in available seats indicator, reserved or not (flag ) edit
  // delete seats once it is sold
}
