package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

/**
 * OrderDetailsList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:31:45.035Z")

public class OrderDetailsList   {
  @JsonProperty("totalPrice")
  private BigDecimal totalPrice = null;

  @JsonProperty("orderedOn")
  private String orderedOn = null;

  @JsonProperty("isPayed")
  private Boolean isPayed = null;

  @JsonProperty("events")
  private List<OrderDetailsListEvents> events = null;

  @JsonProperty("_links")
  private OrderDetailsListLinks links = null;

  public OrderDetailsList totalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
    return this;
  }

   /**
   * Get totalPrice
   * @return totalPrice
  **/


  @Valid

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public OrderDetailsList orderedOn(String orderedOn) {
    this.orderedOn = orderedOn;
    return this;
  }

   /**
   * Get orderedOn
   * @return orderedOn
  **/


  @Valid

  public String getOrderedOn() {
    return orderedOn;
  }

  public void setOrderedOn(String orderedOn) {
    this.orderedOn = orderedOn;
  }

  public OrderDetailsList isPayed(Boolean isPayed) {
    this.isPayed = isPayed;
    return this;
  }

   /**
   * Get isPayed
   * @return isPayed
  **/



  public Boolean getIsPayed() {
    return isPayed;
  }

  public void setIsPayed(Boolean isPayed) {
    this.isPayed = isPayed;
  }

  public OrderDetailsList events(List<OrderDetailsListEvents> events) {
    this.events = events;
    return this;
  }

  public OrderDetailsList addEventsItem(OrderDetailsListEvents eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<OrderDetailsListEvents>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/


  @Valid

  public List<OrderDetailsListEvents> getEvents() {
    return events;
  }

  public void setEvents(List<OrderDetailsListEvents> events) {
    this.events = events;
  }

  public OrderDetailsList links(OrderDetailsListLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/

  @Valid

  public OrderDetailsListLinks getLinks() {
    return links;
  }

  public void setLinks(OrderDetailsListLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetailsList orderDetailsList = (OrderDetailsList) o;
    return Objects.equals(this.totalPrice, orderDetailsList.totalPrice) &&
        Objects.equals(this.orderedOn, orderDetailsList.orderedOn) &&
        Objects.equals(this.isPayed, orderDetailsList.isPayed) &&
        Objects.equals(this.events, orderDetailsList.events) &&
        Objects.equals(this.links, orderDetailsList.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPrice, orderedOn, isPayed, events, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetailsList {\n");
    
    sb.append("    totalPrice: ").append(toIndentedString(totalPrice)).append("\n");
    sb.append("    orderedOn: ").append(toIndentedString(orderedOn)).append("\n");
    sb.append("    isPayed: ").append(toIndentedString(isPayed)).append("\n");
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

