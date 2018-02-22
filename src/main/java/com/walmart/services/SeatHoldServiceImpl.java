package com.walmart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.dao.SeatHoldRepository;
import com.walmart.dto.SeatHold;

@Service
public class SeatHoldServiceImpl implements SeatHoldService {

  private final SeatHoldRepository seatHoldRepository;

  @Autowired
  public SeatHoldServiceImpl(final SeatHoldRepository seatHoldRepository) {
    this.seatHoldRepository = seatHoldRepository;
  }

  @Override
  public SeatHold holdSeats(final SeatHold seatHold) {
    return this.seatHoldRepository.holdSeats(seatHold);
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {
    return this.seatHoldRepository.reserveSeats(seatHoldId, customerEmail);
  }

  @Override
  public String deletedHeldSeat(final int seatHoldId) {
    // TODO Auto-generated method stub
    return null;
  }
}
