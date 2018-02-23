package com.walmart.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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
import com.walmart.util.EmailValidator;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

  @Mock
  private AvailableSeatsService availableSeatsService;

  @Mock
  private SeatHoldService seatHoldService;

  @Mock
  private EmailValidator emailValidator;

  private TicketServiceImpl ticketServiceImpl;

  private final static String EVENT_ID = "A010";
  private final static String SECTION_ID = "220";
  private final static String ROW_ID = "H";
  private final static int SEAT_NUM = 21;
  private final static String STATUS = "A";
  private final static int SEAT_HOLD_ID = 12345;
  private final static String RESERVED = "Successfuly reserved!";

  private final static int NUMBER_OF_SEATS = 2;
  private final static String CUSTOMER_EMAIL = "Abereham.wodajie@gmail.com";
  private final static String INVALID_CUSTOMER_EMAIL = "Abereham.wodajiegmail.com";

  private List<AvailableSeats> availableSeats;
  private SeatHold seatHold;

  @Before
  public void setup() {
    this.ticketServiceImpl =
        new TicketServiceImpl(this.availableSeatsService, this.seatHoldService, this.emailValidator);
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
  public void givenValidInputWhenfindAndHoldSeatsCalled_SuccessTest() {
    this.seatHold.setSeatHoldId(12345);
    final SeatHold seatHeld = this.seatHold;
    when(this.emailValidator.isValidEmailAddress(CUSTOMER_EMAIL)).thenReturn(true);
    when(this.availableSeatsService.findBestAvailableSeats(NUMBER_OF_SEATS)).thenReturn(this.availableSeats);
    when(this.seatHoldService.holdSeats(anyObject())).thenReturn(seatHeld);

    doNothing().when(this.availableSeatsService).seatHold(anyObject());

    final SeatHold seatHoldRespose = this.ticketServiceImpl.findAndHoldSeats(NUMBER_OF_SEATS, CUSTOMER_EMAIL);
    assertEquals(seatHeld, seatHoldRespose);

    verify(this.availableSeatsService, times(1)).findBestAvailableSeats(NUMBER_OF_SEATS);
    verify(this.emailValidator, times(1)).isValidEmailAddress(CUSTOMER_EMAIL);
    verify(this.seatHoldService, times(1)).holdSeats(anyObject());
  }

  @Test
  public void givenInvalidValidInputWhenfindAndHoldSeatsCalled_throwException() {
    this.seatHold.setSeatHoldId(12345);
    final SeatHold seatHeld = this.seatHold;
    when(this.emailValidator.isValidEmailAddress(INVALID_CUSTOMER_EMAIL)).thenReturn(false);
    when(this.availableSeatsService.findBestAvailableSeats(NUMBER_OF_SEATS)).thenReturn(this.availableSeats);
    when(this.seatHoldService.holdSeats(anyObject())).thenReturn(seatHeld);

    doNothing().when(this.availableSeatsService).seatHold(anyObject());

    final Throwable exception = catchThrowable(() -> {
      this.ticketServiceImpl.findAndHoldSeats(NUMBER_OF_SEATS, CUSTOMER_EMAIL);
    });

    assertThat(exception).isInstanceOfAny(IllegalArgumentException.class);

    verify(this.availableSeatsService, times(0)).findBestAvailableSeats(NUMBER_OF_SEATS);
    verify(this.emailValidator, times(1)).isValidEmailAddress(CUSTOMER_EMAIL);
    verify(this.seatHoldService, times(0)).holdSeats(anyObject());
  }


  @Test
  public void givenValidInput_WhenReserveSeatsCalled_ReturnSeatReserveSuccess() {
    when(this.emailValidator.isValidEmailAddress(CUSTOMER_EMAIL)).thenReturn(true);
    when(this.seatHoldService.reserveSeats(SEAT_HOLD_ID, CUSTOMER_EMAIL)).thenReturn(TicketServiceImplTest.RESERVED);
    final String reserveRepose = this.ticketServiceImpl.reserveSeats(SEAT_HOLD_ID, CUSTOMER_EMAIL);

    verify(this.seatHoldService, times(1)).reserveSeats(SEAT_HOLD_ID, CUSTOMER_EMAIL);
    assertEquals(reserveRepose, RESERVED);
    verify(this.emailValidator, times(1)).isValidEmailAddress(CUSTOMER_EMAIL);
  }

  @Test
  public void givenInvalidInput_WhenReserveSeatsCalled_throwException() {
    when(this.emailValidator.isValidEmailAddress(INVALID_CUSTOMER_EMAIL)).thenReturn(false);
    when(this.seatHoldService.reserveSeats(SEAT_HOLD_ID, CUSTOMER_EMAIL)).thenReturn(TicketServiceImplTest.RESERVED);

    final Throwable exception = catchThrowable(() -> {
      this.ticketServiceImpl.findAndHoldSeats(NUMBER_OF_SEATS, CUSTOMER_EMAIL);
    });

    assertThat(exception).isInstanceOfAny(IllegalArgumentException.class);

    verify(this.seatHoldService, times(0)).reserveSeats(SEAT_HOLD_ID, CUSTOMER_EMAIL);
    verify(this.emailValidator, times(1)).isValidEmailAddress(CUSTOMER_EMAIL);
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
