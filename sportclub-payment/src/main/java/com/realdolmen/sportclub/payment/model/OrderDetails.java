package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.math.BigDecimal;
import javax.validation.Valid;

/**
 * OrderDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T09:33:02.939Z")

public class OrderDetails   {
  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("orderedOn")
  private String orderedOn = null;

  @JsonProperty("isPayed")
  private Boolean isPayed = null;

  @JsonProperty("_links")
  private OrderDetailsLinks links = null;

  public OrderDetails price(BigDecimal price) {
    this.price = price;
    return this;
  }

   /**
   * Get price
   * @return price
  **/


  @Valid

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public OrderDetails orderedOn(String orderedOn) {
    this.orderedOn = orderedOn;
    return this;
  }

   /**
   * Get orderedOn
   * @return orderedOn
  **/


  @Valid

  public String getOrderedOn() {
    return orderedOn;
  }

  public void setOrderedOn(String orderedOn) {
    this.orderedOn = orderedOn;
  }

  public OrderDetails isPayed(Boolean isPayed) {
    this.isPayed = isPayed;
    return this;
  }

   /**
   * Get isPayed
   * @return isPayed
  **/



  public Boolean getIsPayed() {
    return isPayed;
  }

  public void setIsPayed(Boolean isPayed) {
    this.isPayed = isPayed;
  }

  public OrderDetails links(OrderDetailsLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/


  @Valid

  public OrderDetailsLinks getLinks() {
    return links;
  }

  public void setLinks(OrderDetailsLinks links) {
    this.links = links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetails orderDetails = (OrderDetails) o;
    return Objects.equals(this.price, orderDetails.price) &&
        Objects.equals(this.orderedOn, orderDetails.orderedOn) &&
        Objects.equals(this.isPayed, orderDetails.isPayed) &&
        Objects.equals(this.links, orderDetails.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, orderedOn, isPayed, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderDetails {\n");
    
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    orderedOn: ").append(toIndentedString(orderedOn)).append("\n");
    sb.append("    isPayed: ").append(toIndentedString(isPayed)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
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

