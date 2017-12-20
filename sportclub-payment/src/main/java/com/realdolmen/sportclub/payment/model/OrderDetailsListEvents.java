package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import javax.validation.Valid;


/**
 * OrderDetailsListEvents
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:31:45.035Z")

public class OrderDetailsListEvents   {
  @JsonProperty("event-uuid")
  private UUID eventUuid = null;

  @JsonProperty("_links")
  private OrderListInnerLinks links = null;

  public OrderDetailsListEvents eventUuid(UUID eventUuid) {
    this.eventUuid = eventUuid;
    return this;
  }

   /**
   * Get eventUuid
   * @return eventUuid
  **/


  @Valid

  public UUID getEventUuid() {
    return eventUuid;
  }

  public void setEventUuid(UUID eventUuid) {
    this.eventUuid = eventUuid;
  }

  public OrderDetailsListEvents links(OrderListInnerLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/


  @Valid

  public OrderListInnerLinks getLinks() {
    return links;
  }

  public void setLinks(OrderListInnerLinks links) {
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
    OrderDetailsListEvents orderDetailsListEvents = (OrderDetailsListEvents) o;
    return Objects.equals(this.eventUuid, orderDetailsListEvents.eventUuid) &&
        Objects.equals(this.links, orderDetailsListEvents.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventUuid, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetailsListEvents {\n");
    
    sb.append("    eventUuid: ").append(toIndentedString(eventUuid)).append("\n");
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

