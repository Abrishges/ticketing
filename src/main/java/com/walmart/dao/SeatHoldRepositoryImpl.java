package com.walmart.dao;

import org.springframework.stereotype.Repository;

import com.walmart.dto.SeatHold;

@Repository
public class SeatHoldRepositoryImpl implements SeatHoldRepository {

  @Override
  public SeatHold holdSeats(final SeatHold seatHold) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String deletedHeldSeat(final int seatHoldId) {
    // TODO Auto-generated method stub
    return null;
  }
  // add seat to seatHold
  // reserve seat
  // delete seats hold when time expired (there will db event that will remove seats with exired
  // time )
  // Edit
  // findseatsHold
}
