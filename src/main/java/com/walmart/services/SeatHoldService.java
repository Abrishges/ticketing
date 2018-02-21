package com.walmart.services;

import com.walmart.dto.SeatHold;

public interface SeatHoldService {
  public SeatHold holdSeats(SeatHold seatHold);

  public String reserveSeats(int seatHoldId, String customerEmail);

  public String deletedHeldSeat(int seatHoldId);
}
