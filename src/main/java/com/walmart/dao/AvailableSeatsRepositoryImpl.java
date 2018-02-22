package com.walmart.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.walmart.dto.AvailableSeats;

@Repository
public class AvailableSeatsRepositoryImpl implements AvailableSeatsRepository {


  final List<AvailableSeats> availableSeats = new ArrayList<>();

  @Override
  public List<AvailableSeats> findAllAvailableSeats() {
    // retrieve data from the data base
    List<AvailableSeats> currentlyAvailseats;
    try {
      currentlyAvailseats = this.availableSeats;
    } catch (final EmptyResultDataAccessException ex) {
      return Collections.emptyList();
    }
    return currentlyAvailseats;
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

  @Override
  public void seatHold(final List<AvailableSeats> bestAvailableSeats) {
    bestAvailableSeats.stream().forEach(seat -> seat.setReserved(true));
  }

  @PostConstruct
  public List<AvailableSeats> populateTestData() {
    final AvailableSeats seatOne = new AvailableSeats("A010", "220", "H", 18, "A");
    final AvailableSeats seatTwo = new AvailableSeats("A010", "230", "L", 12, "A");
    final AvailableSeats seatThree = new AvailableSeats("A010", "240", "M", 20, "A");
    final AvailableSeats seatFour = new AvailableSeats("A010", "250", "N", 25, "A");
    final AvailableSeats seatFive = new AvailableSeats("A010", "260", "Q", 30, "A");
    this.availableSeats.add(seatOne);
    this.availableSeats.add(seatTwo);
    this.availableSeats.add(seatThree);
    this.availableSeats.add(seatFour);
    this.availableSeats.add(seatFive);
    return this.availableSeats;
  }
}
