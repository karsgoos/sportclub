package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * PointsListInnerLinks
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsListInnerLinks   {
  @JsonProperty("user")
  private String user = null;

  @JsonProperty("details")
  private String details = null;

  public PointsListInnerLinks user(String user) {
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

  public PointsListInnerLinks details(String details) {
    this.details = details;
    return this;
  }

   /**
   * Get details
   * @return details
  **/



  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PointsListInnerLinks pointsListInnerLinks = (PointsListInnerLinks) o;
    return Objects.equals(this.user, pointsListInnerLinks.user) &&
        Objects.equals(this.details, pointsListInnerLinks.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsListInnerLinks {\n");
    
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

