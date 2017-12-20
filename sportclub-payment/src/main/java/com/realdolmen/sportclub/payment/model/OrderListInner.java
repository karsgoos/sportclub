package com.realdolmen.sportclub.payment.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.Valid;

/**
 * OrderListInner
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-19T09:33:02.939Z")

public class OrderListInner   {
  @JsonProperty("uuid")
  private UUID uuid = null;

  @JsonProperty("price")
  private BigDecimal price = null;

  @JsonProperty("orderedOn")
  private String orderedOn = null;

  @JsonProperty("isPayed")
  private Boolean isPayed = null;

  @JsonProperty("_links")
  private OrderListInnerLinks links = null;

  public OrderListInner uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

   /**
   * Get uuid
   * @return uuid
  **/

  @Valid

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public OrderListInner price(BigDecimal price) {
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

  public OrderListInner orderedOn(String orderedOn) {
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

  public OrderListInner isPayed(Boolean isPayed) {
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

  public OrderListInner links(OrderListInnerLinks links) {
    this.links = links;
    return this;
  }

   /**
   * Get links
   * @return links
  **/


  @Valid

  public OrderListInnerLinks getLinks() {
    return links;
  }

  public void setLinks(OrderListInnerLinks links) {
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
    OrderListInner orderListInner = (OrderListInner) o;
    return Objects.equals(this.uuid, orderListInner.uuid) &&
        Objects.equals(this.price, orderListInner.price) &&
        Objects.equals(this.orderedOn, orderListInner.orderedOn) &&
        Objects.equals(this.isPayed, orderListInner.isPayed) &&
        Objects.equals(this.links, orderListInner.links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, price, orderedOn, isPayed, links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrderListInner {\n");
    
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
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

