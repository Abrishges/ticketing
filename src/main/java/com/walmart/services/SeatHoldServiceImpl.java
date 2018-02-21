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
    // TODO Auto-generated method stub
    this.seatHoldRepository.holdSeats(seatHold);
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
}
