package com.walmart.dao;

import java.util.List;

import com.walmart.dto.AvailableSeats;

public interface AvailableSeatsRepository {
  // return all available seats, seats which are available
  // delete seats once it is sold

  public List<AvailableSeats> findAllAvailableSeats();

  public void deleteAvailableSeats(String eventId, String sectionId, String rowId, String seatNum);

  public AvailableSeats editAvailableSeat(AvailableSeats availableSeats);

  public void seatHold(final List<AvailableSeats> bestAvailableSeats);

  public void editAvailableSeat(final List<AvailableSeats> availableSeats);
}
