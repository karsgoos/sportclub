package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;
import javax.validation.Valid;


/**
 * PointsListInner
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsListInner   {
  @JsonProperty("user-id")
  private UUID userId = null;

  @JsonProperty("points")
  private Integer points = null;

  @JsonProperty("_links")
  private PointsListInnerLinks links = null;

  public PointsListInner userId(UUID userId) {
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

  public PointsListInner points(Integer points) {
    this.points = points;
    return this;
  }

   /**
   * Get points
   * @return points
  **/



  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public PointsListInner links(PointsListInnerLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/


  @Valid

  public PointsListInnerLinks getLinks() {
    return links;
  }

  public void setLinks(PointsListInnerLinks links) {
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
    PointsListInner pointsListInner = (PointsListInner) o;
    return Objects.equals(this.userId, pointsListInner.userId) &&
        Objects.equals(this.points, pointsListInner.points) &&
        Objects.equals(this.links, pointsListInner.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, points, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsListInner {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    points: ").append(toIndentedString(points)).append("\n");
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

