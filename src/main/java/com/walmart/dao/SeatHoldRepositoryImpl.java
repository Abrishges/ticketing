package com.walmart.dao;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.google.common.primitives.Ints;
import com.walmart.dto.SeatHold;

@Repository
public class SeatHoldRepositoryImpl implements SeatHoldRepository {

  final List<SeatHold> seatsHold = new ArrayList<>();
  private final static String RESERVED = "Successfuly reserved!";
  private final static String NOTRESERVED = "Seat Hold Id NOT found!";
  private static boolean reserveStatus;

  @Override
  public SeatHold holdSeats(final SeatHold seatHold) {
    final Instant instant = Instant.now();
    final long timeStampSeconds = instant.getEpochSecond();
    seatHold.setSeatHoldId(Ints.checkedCast(timeStampSeconds));
    this.seatsHold.add(seatHold);
    return seatHold;
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {

    this.seatsHold.stream().forEach(seat -> {
      if (seat.getSeatHoldId() == seatHoldId) {
        seat.setReserved(true);
        SeatHoldRepositoryImpl.reserveStatus = true;
      }
    });
    if (SeatHoldRepositoryImpl.reserveStatus) {
      SeatHoldRepositoryImpl.reserveStatus = false;
      return RESERVED;
    }
    return NOTRESERVED;
  }

  @Override
  public String deletedHeldSeat(final int seatHoldId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<SeatHold> getAllHoldSeats() {
    // This method returns all hold seats from db
    return this.seatsHold;
  }

  @Override
  public List<SeatHold> findAllHoldSeatsBySeatHoldId(final int seatHoldId) {
    // This method returns all hold seats with the same same seatHoldId from db
    final List<SeatHold> seatHoldById =
        this.seatsHold.stream().filter(seat -> seat.getSeatHoldId() == seatHoldId).collect(Collectors.toList());
    return seatHoldById;
  }
}
