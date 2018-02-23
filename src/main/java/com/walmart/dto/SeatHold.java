package com.walmart.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class SeatHold {

  private int seatHoldId;
  private List<AvailableSeats> availableSeats;

  @NotNull
  private String releaseDatetime; // feature release data time
  private boolean reserved; // If true, no need to check the time
  private String promoCode;

  private String customerId;
  @NotNull
  private String customerEmail;
  private String orderNumber;
  private String dateTime;

  public List<AvailableSeats> getAvailableSeats() {
    return this.availableSeats;
  }

  public void setAvailableSeats(final List<AvailableSeats> availableSeats) {
    this.availableSeats = availableSeats;
  }

  public String getReleaseDatetime() {
    return this.releaseDatetime;
  }

  public void setReleaseDatetime(final String releaseDatetime) {
    this.releaseDatetime = releaseDatetime;
  }

  public int getSeatHoldId() {
    return this.seatHoldId;
  }

  public void setSeatHoldId(final int seatHoldId) {
    this.seatHoldId = seatHoldId;
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
