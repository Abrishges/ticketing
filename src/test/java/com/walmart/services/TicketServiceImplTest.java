package com.walmart.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.walmart.dto.AvailableSeats;
import com.walmart.dto.SeatHold;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

  @Mock
  private AvailableSeatsService availableSeatsService;

  @Mock
  private SeatHoldService seatHoldService;

  private TicketServiceImpl ticketServiceImpl;

  private final static String EVENT_ID = "A010";
  private final static String SECTION_ID = "220";
  private final static String ROW_ID = "H";
  private final static int SEAT_NUM = 21;
  private final static String STATUS = "A";

  private final static int NUMBER_OF_SEATS = 2;
  private final static String CUSTOMER_EMAIL = "Abereham.wodajie@gmail.com";


  private List<AvailableSeats> availableSeats;
  private SeatHold seatHold;

  @Before
  public void setup() {
    this.ticketServiceImpl = new TicketServiceImpl(this.availableSeatsService, this.seatHoldService);
    this.availableSeats = this.getAvailableSeats();
    this.seatHold = this.getSeatHold();
  }

  @Test
  public void numSeatsAvailableSuccessTest() {
    when(this.availableSeatsService.findAllAvailableSeats()).thenReturn(this.availableSeats);
    final int numberOfSeats = this.ticketServiceImpl.numSeatsAvailable();
    assertEquals(this.availableSeats.size(), numberOfSeats);
    verify(this.availableSeatsService, times(1)).findAllAvailableSeats();
  }

  @Test
  public void findAndHoldSeatsSuccessTest() {
    this.seatHold.setSeatHoldId(12345);
    final SeatHold seatHeld = this.seatHold;
    when(this.availableSeatsService.findBestAvailableSeats(NUMBER_OF_SEATS)).thenReturn(this.availableSeats);
    when(this.seatHoldService.holdSeats(anyObject())).thenReturn(seatHeld);

    doNothing().when(this.availableSeatsService).seatHold(anyObject());

    final SeatHold seatHoldRespose = this.ticketServiceImpl.findAndHoldSeats(NUMBER_OF_SEATS, CUSTOMER_EMAIL);
    assertEquals(seatHeld, seatHoldRespose);
    verify(this.availableSeatsService, times(1)).findBestAvailableSeats(NUMBER_OF_SEATS);
    verify(this.seatHoldService, times(1)).holdSeats(anyObject());
  }

  public List<AvailableSeats> getAvailableSeats() {
    final AvailableSeats seatOne = new AvailableSeats(EVENT_ID, SECTION_ID, ROW_ID, SEAT_NUM, STATUS);
    this.availableSeats = new ArrayList<>();
    this.availableSeats.add(seatOne);
    return this.availableSeats;
  }

  public SeatHold getSeatHold() {
    final SeatHold seatHold = new SeatHold();
    seatHold.setAvailableSeats(this.availableSeats);
    return seatHold;
  }
}
