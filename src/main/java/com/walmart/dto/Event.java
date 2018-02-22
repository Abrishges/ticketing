
package com.walmart.dto;

import javax.validation.constraints.NotNull;

public class Event {
  @NotNull
  private String eventId;
  @NotNull
  private String eventName;
  private String seasonId;
  private String eventDate;
  private String eventTime;
  private String eventDay;

  public String getEventId() {
    return this.eventId;
  }

  public void setEventId(final String eventId) {
    this.eventId = eventId;
  }

  public String getEventName() {
    return this.eventName;
  }

  public void setEventName(final String eventName) {
    this.eventName = eventName;
  }

  public String getSeasonId() {
    return this.seasonId;
  }

  public void setSeasonId(final String seasonId) {
    this.seasonId = seasonId;
  }

  public String getEventDate() {
    return this.eventDate;
  }

  public void setEventDate(final String eventDate) {
    this.eventDate = eventDate;
  }

  public String getEventTime() {
    return this.eventTime;
  }

  public void setEventTime(final String eventTime) {
    this.eventTime = eventTime;
  }

  public String getEventDay() {
    return this.eventDay;
  }

  public void setEventDay(final String eventDay) {
    this.eventDay = eventDay;
  }
}
