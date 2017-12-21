package com.realdolmen.sportclub.events.controller.DTO;

import java.time.LocalDate;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;

/**
 * EventListInner
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-20T13:53:37.870Z")

public class EventListInner   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("start-date")
  private String startDate = null;

  @JsonProperty("end-date")
  private String endDate = null;

  @JsonProperty("deadline")
  private String deadline = null;

  @JsonProperty("_links")
  private EventListInnerLinks links = null;

  public EventListInner name(String name) {
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

  public EventListInner startDate(String startDate) {
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

  public EventListInner endDate(String endDate) {
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

  public EventListInner deadline(String deadline) {
    this.deadline = deadline;
    return this;
  }

   /**
   * Get isOpen
   * @return isOpen
  **/
  @ApiModelProperty(value = "")



  public EventListInner links(EventListInnerLinks links) {
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
    EventListInner eventListInner = (EventListInner) o;
    return Objects.equals(this.name, eventListInner.name) &&
        Objects.equals(this.startDate, eventListInner.startDate) &&
        Objects.equals(this.endDate, eventListInner.endDate) &&
        Objects.equals(this.deadline, eventListInner.deadline) &&
        Objects.equals(this.links, eventListInner.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, startDate, endDate, deadline, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EventListInner {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    isOpen: ").append(toIndentedString(deadline)).append("\n");
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

  public String getDeadline() {
    return deadline;
  }

  public void setDeadline(String deadline) {
    this.deadline = deadline;
  }
}

