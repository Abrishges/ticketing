package com.walmart.services;

import java.util.List;

import com.walmart.dto.AvailableSeats;

public interface AvailableSeatsService {
  public List<AvailableSeats> findAllAvailableSeats();

  public List<AvailableSeats> findBestAvailableSeats(int numSeats);

  public void deleteAvailableSeats(String eventId, String sectionId, String rowId, String seatNum);

  public void reserveSeats(List<AvailableSeats> availableSeats);

  public AvailableSeats editAvailableSeat(AvailableSeats availableSeats);

  public void seatHold(final List<AvailableSeats> bestAvailableSeats);
}

