package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;


/**
 * PointsDetailsLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsDetailsLinks   {
  @JsonProperty("self")
  private String self = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("events")
  private List<PointsDetailsLinksEvents> events = null;

  public PointsDetailsLinks self(String self) {
    this.self = self;
    return this;
  }

   /**
   * Get self
   * @return self
  **/



  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

  public PointsDetailsLinks user(String user) {
    this.user = user;
    return this;
  }

   /**
   * Get user
   * @return user
  **/



  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public PointsDetailsLinks events(List<PointsDetailsLinksEvents> events) {
    this.events = events;
    return this;
  }

  public PointsDetailsLinks addEventsItem(PointsDetailsLinksEvents eventsItem) {
    if (this.events == null) {
      this.events = new ArrayList<PointsDetailsLinksEvents>();
    }
    this.events.add(eventsItem);
    return this;
  }

   /**
   * Get events
   * @return events
  **/


  @Valid

  public List<PointsDetailsLinksEvents> getEvents() {
    return events;
  }

  public void setEvents(List<PointsDetailsLinksEvents> events) {
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
    PointsDetailsLinks pointsDetailsLinks = (PointsDetailsLinks) o;
    return Objects.equals(this.self, pointsDetailsLinks.self) &&
        Objects.equals(this.user, pointsDetailsLinks.user) &&
        Objects.equals(this.events, pointsDetailsLinks.events);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, user, events);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsDetailsLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

