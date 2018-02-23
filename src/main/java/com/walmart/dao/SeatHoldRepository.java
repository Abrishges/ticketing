package com.walmart.dao;

import java.util.List;

import com.walmart.dto.SeatHold;

public interface SeatHoldRepository {

  public SeatHold holdSeats(SeatHold seatHold);

  public String reserveSeats(int seatHoldId, String customerEmail);

  public String deletedHeldSeat(int seatHoldId);

  public List<SeatHold> getAllHoldSeats();

  public List<SeatHold> findAllHoldSeatsBySeatHoldId(final int seatHoldId);
}
