package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import javax.validation.Valid;


/**
 * PointsInputEvents
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsInputEvents   {
  @JsonProperty("event")
  private UUID event = null;

  public PointsInputEvents event(UUID event) {
    this.event = event;
    return this;
  }

   /**
   * Get event
   * @return event
  **/


  @Valid

  public UUID getEvent() {
    return event;
  }

  public void setEvent(UUID event) {
    this.event = event;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointsInputEvents pointsInputEvents = (PointsInputEvents) o;
    return Objects.equals(this.event, pointsInputEvents.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(event);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsInputEvents {\n");
    
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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

