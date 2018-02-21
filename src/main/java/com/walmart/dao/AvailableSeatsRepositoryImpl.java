package com.walmart.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.walmart.dto.AvailableSeats;

@Repository
public class AvailableSeatsRepositoryImpl implements AvailableSeatsRepository {

  @Override
  public List<AvailableSeats> findAllAvailableSeats() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteAvailableSeats(
      final String eventId,
      final String sectionId,
      final String rowId,
      final String seatNum) {
    // TODO Auto-generated method stub

  }

  @Override
  public void reserveSeats(final List<AvailableSeats> availableSeats) {
    // TODO Auto-generated method stub

  }

  @Override
  public AvailableSeats editAvailableSeat(final AvailableSeats availableSeats) {
    // TODO Auto-generated method stub
    return null;
  }
}
