package com.realdolmen.sportclub.events.controller.DTO;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import javax.validation.Valid;

/**
 * EventDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-20T13:53:37.870Z")

public class EventDetails   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("start-date")
  private String startDate = null;

  @JsonProperty("end-date")
  private String endDate = null;

  @JsonProperty("deadline")
  private String deadline = null;

  @JsonProperty("price-adult")
  private BigDecimal priceAdult = null;

  @JsonProperty("price-child")
  private BigDecimal priceChild = null;

  @JsonProperty("min-participants")
  private Integer minParticipants = null;

  @JsonProperty("max-participants")
  private Integer maxParticipants = null;

  @JsonProperty("points")
  private Integer points = null;

  @JsonProperty("_links")
  private EventListInnerLinks links = null;

  public EventDetails name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public EventDetails description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public EventDetails startDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * Get startDate
   * @return startDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public EventDetails endDate(String endDate) {
    this.endDate = endDate;
    return this;
  }

   /**
   * Get endDate
   * @return endDate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public EventDetails deadline(String isOpen) {
    this.deadline = isOpen;
    return this;
  }

   /**
   * Get deadline
   * @return deadline
  **/
  @ApiModelProperty(value = "")


  public String getDeadline() {
    return deadline;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }

  public EventDetails priceAdult(BigDecimal priceAdult) {
    this.priceAdult = priceAdult;
    return this;
  }

   /**
   * Get priceAdult
   * @return priceAdult
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getPriceAdult() {
    return priceAdult;
  }

  public void setPriceAdult(BigDecimal priceAdult) {
    this.priceAdult = priceAdult;
  }

  public EventDetails priceChild(BigDecimal priceChild) {
    this.priceChild = priceChild;
    return this;
  }

   /**
   * Get priceChild
   * @return priceChild
  **/
  @ApiModelProperty(value = "")

  @Valid

  public BigDecimal getPriceChild() {
    return priceChild;
  }

  public void setPriceChild(BigDecimal priceChild) {
    this.priceChild = priceChild;
  }

  public EventDetails minParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
    return this;
  }

   /**
   * Get minParticipants
   * @return minParticipants
  **/
  @ApiModelProperty(value = "")


  public Integer getMinParticipants() {
    return minParticipants;
  }

  public void setMinParticipants(Integer minParticipants) {
    this.minParticipants = minParticipants;
  }

  public EventDetails maxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
    return this;
  }

   /**
   * Get maxParticipants
   * @return maxParticipants
  **/
  @ApiModelProperty(value = "")


  public Integer getMaxParticipants() {
    return maxParticipants;
  }

  public void setMaxParticipants(Integer maxParticipants) {
    this.maxParticipants = maxParticipants;
  }

  public EventDetails points(Integer points) {
    this.points = points;
    return this;
  }

   /**
   * Get points
   * @return points
  **/
  @ApiModelProperty(value = "")


  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }

  public EventDetails links(EventListInnerLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/
  @ApiModelProperty(value = "")

  @Valid

  public EventListInnerLinks getLinks() {
    return links;
  }

  public void setLinks(EventListInnerLinks links) {
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
    EventDetails eventDetails = (EventDetails) o;
    return Objects.equals(this.name, eventDetails.name) &&
        Objects.equals(this.description, eventDetails.description) &&
        Objects.equals(this.startDate, eventDetails.startDate) &&
        Objects.equals(this.endDate, eventDetails.endDate) &&
        Objects.equals(this.deadline, eventDetails.deadline) &&
        Objects.equals(this.priceAdult, eventDetails.priceAdult) &&
        Objects.equals(this.priceChild, eventDetails.priceChild) &&
        Objects.equals(this.minParticipants, eventDetails.minParticipants) &&
        Objects.equals(this.maxParticipants, eventDetails.maxParticipants) &&
        Objects.equals(this.points, eventDetails.points) &&
        Objects.equals(this.links, eventDetails.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, startDate, endDate, deadline, priceAdult, priceChild, minParticipants, maxParticipants, points, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventDetails {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    deadline: ").append(toIndentedString(deadline)).append("\n");
    sb.append("    priceAdult: ").append(toIndentedString(priceAdult)).append("\n");
    sb.append("    priceChild: ").append(toIndentedString(priceChild)).append("\n");
    sb.append("    minParticipants: ").append(toIndentedString(minParticipants)).append("\n");
    sb.append("    maxParticipants: ").append(toIndentedString(maxParticipants)).append("\n");
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

