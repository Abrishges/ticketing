package com.walmart.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Ticket {
  @NotNull
  private String eventId;
  @NotNull
  private String sectionId;
  @NotNull
  private String rowId;
  @NotNull
  private String seatNumber;

  private String priceCode;
  private String fullPrice;
  private String discountCode;
  private BigDecimal discountAmount;
  private String aisle;
  private String addDateTime;
  private String ticketTypeCode;

  @NotNull
  private String releaseDatetime;
  private String reservedInd; // If y, no need to check the time

  private String customerId;
  @NotNull
  private String customerEmail;
  @NotNull
  private String orderNumber;

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

  public String getSeatNumber() {
    return this.seatNumber;
  }

  public void setSeatNumber(final String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public String getPriceCode() {
    return this.priceCode;
  }

  public void setPriceCode(final String priceCode) {
    this.priceCode = priceCode;
  }

  public String getFullPrice() {
    return this.fullPrice;
  }

  public void setFullPrice(final String fullPrice) {
    this.fullPrice = fullPrice;
  }

  public String getDiscountCode() {
    return this.discountCode;
  }

  public void setDiscountCode(final String discountCode) {
    this.discountCode = discountCode;
  }

  public BigDecimal getDiscountAmount() {
    return this.discountAmount;
  }

  public void setDiscountAmount(final BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public String getAisle() {
    return this.aisle;
  }

  public void setAisle(final String aisle) {
    this.aisle = aisle;
  }

  public String getAddDateTime() {
    return this.addDateTime;
  }

  public void setAddDateTime(final String addDateTime) {
    this.addDateTime = addDateTime;
  }

  public String getTicketTypeCode() {
    return this.ticketTypeCode;
  }

  public void setTicketTypeCode(final String ticketTypeCode) {
    this.ticketTypeCode = ticketTypeCode;
  }

  public String getReleaseDatetime() {
    return this.releaseDatetime;
  }

  public void setReleaseDatetime(final String releaseDatetime) {
    this.releaseDatetime = releaseDatetime;
  }

  public String getReservedInd() {
    return this.reservedInd;
  }

  public void setReservedInd(final String reservedInd) {
    this.reservedInd = reservedInd;
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
}
