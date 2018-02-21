package com.walmart.dao;

import com.walmart.dto.SeatHold;

public interface SeatHoldRepository {

  public SeatHold holdSeats(SeatHold seatHold);

  public String reserveSeats(int seatHoldId, String customerEmail);

  public String deletedHeldSeat(int seatHoldId);
}
