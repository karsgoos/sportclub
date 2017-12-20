package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * OrderDetailsLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T09:33:02.939Z")

public class OrderDetailsLinks   {
  @JsonProperty("self")
  private String self = null;

  @JsonProperty("user")
  private String user = null;

  @JsonProperty("event")
  private String event = null;

  public OrderDetailsLinks self(String self) {
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

  public OrderDetailsLinks user(String user) {
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

  public OrderDetailsLinks event(String event) {
    this.event = event;
    return this;
  }

   /**
   * Get event
   * @return event
  **/



  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetailsLinks orderDetailsLinks = (OrderDetailsLinks) o;
    return Objects.equals(this.self, orderDetailsLinks.self) &&
        Objects.equals(this.user, orderDetailsLinks.user) &&
        Objects.equals(this.event, orderDetailsLinks.event);
  }

  @Override
  public int hashCode() {
    return Objects.hash(self, user, event);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetailsLinks {\n");
    
    sb.append("    self: ").append(toIndentedString(self)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    event: ").append(toIndentedString(event)).append("\n");
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

