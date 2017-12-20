package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.Valid;

/**
 * OrderInputEvents
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T09:33:02.939Z")

public class OrderInputEvents   {
  @JsonProperty("event-uuid")
  private UUID eventUuid = null;

  public OrderInputEvents eventUuid(UUID eventUuid) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderInputEvents orderInputEvents = (OrderInputEvents) o;
    return Objects.equals(this.eventUuid, orderInputEvents.eventUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(eventUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderInputEvents {\n");
    
    sb.append("    eventUuid: ").append(toIndentedString(eventUuid)).append("\n");
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

