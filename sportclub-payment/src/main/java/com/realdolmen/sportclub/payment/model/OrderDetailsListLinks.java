package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * OrderDetailsListLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:31:45.035Z")

public class OrderDetailsListLinks   {
  @JsonProperty("user")
  private String user = null;

  public OrderDetailsListLinks user(String user) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetailsListLinks orderDetailsListLinks = (OrderDetailsListLinks) o;
    return Objects.equals(this.user, orderDetailsListLinks.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetailsListLinks {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

