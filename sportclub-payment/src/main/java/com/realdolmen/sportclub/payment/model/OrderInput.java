package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

/**
 * OrderInput
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T09:33:02.939Z")

public class OrderInput   {
  @JsonProperty("userId")
  private UUID userId = null;

  @JsonProperty("events")
  private List<OrderInputEvents> events = null;

  public OrderInput userId(UUID userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Get userId
   * @return userId
  **/


  @Valid

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public OrderInput events(List<OrderInputEvents> events) {
    this.events = events;
    return this;
  }

  public OrderInput addEventsItem(OrderInputEvents eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<OrderInputEvents>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/


  @Valid

  public List<OrderInputEvents> getEvents() {
    return events;
  }

  public void setEvents(List<OrderInputEvents> events) {
    this.events = events;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderInput orderInput = (OrderInput) o;
    return Objects.equals(this.userId, orderInput.userId) &&
        Objects.equals(this.events, orderInput.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderInput {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

