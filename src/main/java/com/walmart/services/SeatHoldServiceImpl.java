package com.walmart.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.walmart.dao.AvailableSeatsRepository;
import com.walmart.dao.SeatHoldRepository;
import com.walmart.dto.SeatHold;

@Service
public class SeatHoldServiceImpl implements SeatHoldService {

  private final SeatHoldRepository seatHoldRepository;
  private final AvailableSeatsRepository availableSeatsRepository;

  @Autowired
  public SeatHoldServiceImpl(
      final SeatHoldRepository seatHoldRepository,
      final AvailableSeatsRepository availableSeatsRepository) {
    this.seatHoldRepository = seatHoldRepository;
    this.availableSeatsRepository = availableSeatsRepository;
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

  // change reserve status in available seats indicator, reserved or not (flag ) edit
  @Scheduled(fixedRate = 60000)
  public void releaseSeats() {
    final List<SeatHold> seatHold = this.seatHoldRepository.getAllHoldSeats();
    seatHold.stream().forEach(seat -> {
      if (LocalDateTime.parse(seat.getReleaseDatetime()).isBefore(LocalDateTime.now()) && !seat.isReserved()) {
        this.seatHoldRepository.deletedHeldSeat(seat.getSeatHoldId());
        this.availableSeatsRepository.editAvailableSeat(seat.getAvailableSeats());
      }
    });
  }
}
