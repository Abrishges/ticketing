package com.walmart.dto;

import java.math.BigDecimal;

public class AvailableSeats {

  private String eventId;
  private String sectionId;
  private String rowId;
  private Integer seatNum;


  private String aisle;
  private String discountCode;
  private BigDecimal price;
  private BigDecimal discountAmount;
  private String ticketType;

  private boolean reserved;
  private String updateUser;
  private String updateDateTime;
  private String sellLocation;

  private String status; // A , I

  public AvailableSeats(
      final String eventId,
      final String sectionId,
      final String rowId,
      final Integer seatNum,
      final String status) {
    this.eventId = eventId;
    this.sectionId = sectionId;
    this.rowId = rowId;
    this.seatNum = seatNum;
    this.status = status;
  }

  public String getEventId() {
    return this.eventId;
  }

  public void setEventId(final String eventId) {
    this.eventId = eventId;
  }

  public String getSectionId() {
    return this.sectionId;
  }

  public void setSectionId(final String sectionId) {
    this.sectionId = sectionId;
  }

  public String getRowId() {
    return this.rowId;
  }

  public void setRowId(final String rowId) {
    this.rowId = rowId;
  }

  public Integer getSeatNum() {
    return this.seatNum;
  }

  public void setSeatNum(final Integer seatNum) {
    this.seatNum = seatNum;
  }

  public String getAisle() {
    return this.aisle;
  }

  public void setAisle(final String aisle) {
    this.aisle = aisle;
  }

  public String getDiscountCode() {
    return this.discountCode;
  }

  public void setDiscountCode(final String discountCode) {
    this.discountCode = discountCode;
  }

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(final BigDecimal price) {
    this.price = price;
  }

  public BigDecimal getDiscountAmount() {
    return this.discountAmount;
  }

  public void setDiscountAmount(final BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public String getTicketType() {
    return this.ticketType;
  }

  public void setTicketType(final String ticketType) {
    this.ticketType = ticketType;
  }

  public boolean isReserved() {
    return this.reserved;
  }

  public void setReserved(final boolean reserved) {
    this.reserved = reserved;
  }

  public String getUpdateUser() {
    return this.updateUser;
  }

  public void setUpdateUser(final String updateUser) {
    this.updateUser = updateUser;
  }

  public String getUpdateDateTime() {
    return this.updateDateTime;
  }

  public void setUpdateDateTime(final String updateDateTime) {
    this.updateDateTime = updateDateTime;
  }

  public String getSellLocation() {
    return this.sellLocation;
  }

  public void setSellLocation(final String sellLocation) {
    this.sellLocation = sellLocation;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(final String status) {
    this.status = status;
  }
}
