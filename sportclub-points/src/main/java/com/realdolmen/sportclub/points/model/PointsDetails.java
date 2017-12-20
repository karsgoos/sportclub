package com.realdolmen.sportclub.points.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;


/**
 * PointsDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T13:59:12.091Z")

public class PointsDetails   {
  @JsonProperty("totalPoints")
  private Integer totalPoints = null;

  @JsonProperty("_links")
  private PointsDetailsLinks links = null;

  public PointsDetails totalPoints(Integer totalPoints) {
    this.totalPoints = totalPoints;
    return this;
  }

   /**
   * Get totalPoints
   * @return totalPoints
  **/



  public Integer getTotalPoints() {
    return totalPoints;
  }

  public void setTotalPoints(Integer totalPoints) {
    this.totalPoints = totalPoints;
  }

  public PointsDetails links(PointsDetailsLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/


  @Valid

  public PointsDetailsLinks getLinks() {
    return links;
  }

  public void setLinks(PointsDetailsLinks links) {
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
    PointsDetails pointsDetails = (PointsDetails) o;
    return Objects.equals(this.totalPoints, pointsDetails.totalPoints) &&
        Objects.equals(this.links, pointsDetails.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalPoints, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PointsDetails {\n");
    
    sb.append("    totalPoints: ").append(toIndentedString(totalPoints)).append("\n");
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

