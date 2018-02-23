
package com.walmart.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.dao.AvailableSeatsRepository;
import com.walmart.dto.AvailableSeats;

@Service
public class AvailableSeatsServiceImpl implements AvailableSeatsService {

  private final AvailableSeatsRepository availableSeatsRepository;

  public static final String SEAT_STATUS = "eventId";

  @Autowired
  public AvailableSeatsServiceImpl(final AvailableSeatsRepository availableSeatsRepository) {
    this.availableSeatsRepository = availableSeatsRepository;
  }

  @Override
  public List<AvailableSeats> findAllAvailableSeats() {
    final List<AvailableSeats> availableSeats = this.availableSeatsRepository.findAllAvailableSeats();
    final List<AvailableSeats> currentAvailableSeats =
        availableSeats.stream().filter(seat -> !seat.getStatus().equals(SEAT_STATUS) && !seat.isReserved()).collect(
            Collectors.toList());
    return currentAvailableSeats;
  }

  /*
   * Seller can develop best available algorithm based on
   * - Price levels or range
   * - sections of the Venue,
   * - different ticket types
   * - Seats on promotions
   * - Best seats with low price
   * - mostly seats at the middle with consumer specified range are best available seats
   */
  @Override
  public List<AvailableSeats> findBestAvailableSeats(final int numSeats) {
    final List<AvailableSeats> availableSeats = this.findAllAvailableSeats();
    // availableSeats are filtered using best algorithm to find Best available seats

    // For this home work seats b/n seat number 20 to 40 are considered as best seats
    final List<AvailableSeats> bestAvailableSeats = availableSeats
        .stream()
        .filter(seat -> seat.getSeatNum() >= 20 && seat.getSeatNum() <= 40)
        .limit(numSeats)
        .collect(Collectors.toList());

    return bestAvailableSeats;
  }

  @Override
  public void seatHold(final List<AvailableSeats> bestAvailableSeats) {
    this.availableSeatsRepository.seatHold(bestAvailableSeats);
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
