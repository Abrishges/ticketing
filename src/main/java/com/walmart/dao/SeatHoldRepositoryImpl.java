package com.walmart.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.walmart.dto.SeatHold;

@Repository
public class SeatHoldRepositoryImpl implements SeatHoldRepository {

  final List<SeatHold> seatsHold = new ArrayList<>();
  private final static String RESERVED = "Successfuly reserved!";

  @Override
  public SeatHold holdSeats(final SeatHold seatHold) {
    final Instant instant = Instant.now();
    final long timeStampSeconds = instant.getEpochSecond();
    seatHold.setSeatHoldId(timeStampSeconds);
    this.seatsHold.add(seatHold);
    return seatHold;
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {
    this.seatsHold.stream().forEach(seat -> {
      if (seat.getSeatHoldId() == seatHoldId) {
        seat.setReserved(true);
      }
    });
    return RESERVED;
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
