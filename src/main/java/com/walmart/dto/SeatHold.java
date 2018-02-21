package com.walmart.dto;

import java.time.LocalDateTime;
import java.util.List;

public class SeatHold {
  private String seatHoldId;
  private List<AvailableSeats> availableSeats;

  // SeatHold
  private LocalDateTime releaseDatetime; // feature release data time
  private boolean reserved; // If true, no need to check the time
  private String promoCode;

  private String customerId;
  private String customerEmail;
  private String orderNumber;
  private String dateTime;

  public String getSeatHoldId() {
    return this.seatHoldId;
  }

  public void setSeatHoldId(final String seatHoldId) {
    this.seatHoldId = seatHoldId;
  }

  public List<AvailableSeats> getAvailableSeats() {
    return this.availableSeats;
  }

  public void setAvailableSeats(final List<AvailableSeats> availableSeats) {
    this.availableSeats = availableSeats;
  }

  public LocalDateTime getReleaseDatetime() {
    return this.releaseDatetime;
  }

  public void setReleaseDatetime(final LocalDateTime releaseDatetime) {
    this.releaseDatetime = releaseDatetime;
  }

  public boolean isReserved() {
    return this.reserved;
  }

  public void setReserved(final boolean reserved) {
    this.reserved = reserved;
  }

  public String getPromoCode() {
    return this.promoCode;
  }

  public void setPromoCode(final String promoCode) {
    this.promoCode = promoCode;
  }

  public String getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(final String customerId) {
    this.customerId = customerId;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public void setCustomerEmail(final String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public String getOrderNumber() {
    return this.orderNumber;
  }

  public void setOrderNumber(final String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getDateTime() {
    return this.dateTime;
  }

  public void setDateTime(final String dateTime) {
    this.dateTime = dateTime;
  }
}
