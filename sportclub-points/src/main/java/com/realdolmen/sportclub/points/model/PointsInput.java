package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;


/**
 * PointsInput
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsInput   {
  @JsonProperty("events")
  private List<PointsInputEvents> events = null;

  public PointsInput events(List<PointsInputEvents> events) {
    this.events = events;
    return this;
  }

  public PointsInput addEventsItem(PointsInputEvents eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<PointsInputEvents>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/


  @Valid

  public List<PointsInputEvents> getEvents() {
    return events;
  }

  public void setEvents(List<PointsInputEvents> events) {
    this.events = events;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointsInput pointsInput = (PointsInput) o;
    return Objects.equals(this.events, pointsInput.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsInput {\n");
    
    sb.append("    events: ").append(toIndentedString(events)).append("\n");
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

