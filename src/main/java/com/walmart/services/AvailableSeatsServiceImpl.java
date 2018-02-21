
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

  @Autowired
  public AvailableSeatsServiceImpl(final AvailableSeatsRepository availableSeatsRepository) {
    this.availableSeatsRepository = availableSeatsRepository;
  }

  @Override
  public List<AvailableSeats> findAllAvailableSeats() {
    final List<AvailableSeats> availableSeats = this.availableSeatsRepository.findAllAvailableSeats();
    final List<AvailableSeats> bestAvailableSeats =
        availableSeats.stream().filter(seat -> seat.getStatus().equals("I") && !seat.isReserved()).collect(
            Collectors.toList());
    return bestAvailableSeats;
  }

  @Override
  public List<AvailableSeats> findBestAvailableSeats(final int numSeats) {
    final List<AvailableSeats> availableSeats = this.findAllAvailableSeats();
    // availableSeats are filtered using best algorithm to find Best available seats
    /*
     *
     * - Price levels or range (
     * - sections of the venue,
     * - different ticket types
     * - Seats on promotions
     * - Best seats with low price
     * - seats at the middle are best available seats
     */

    final List<AvailableSeats> bestAvailableSeats = availableSeats;

    return bestAvailableSeats;
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
