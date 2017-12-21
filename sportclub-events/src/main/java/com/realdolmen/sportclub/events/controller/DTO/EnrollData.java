package com.realdolmen.sportclub.events.controller.DTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EnrollData
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-20T13:53:37.870Z")

public class EnrollData   {
  @JsonProperty("user-uuid")
  private UUID userUuid = null;

  @JsonProperty("amountChildren")
  private Integer amountChildren = null;

  @JsonProperty("amountAdults")
  private Integer amountAdults = null;

  public EnrollData userUuid(UUID userUuid) {
    this.userUuid = userUuid;
    return this;
  }

   /**
   * Get userUuid
   * @return userUuid
  **/
  @ApiModelProperty(value = "")

  @Valid

  public UUID getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(UUID userUuid) {
    this.userUuid = userUuid;
  }

  public EnrollData amountChildren(Integer amountChildren) {
    this.amountChildren = amountChildren;
    return this;
  }

   /**
   * Get amountChildren
   * @return amountChildren
  **/
  @ApiModelProperty(value = "")


  public Integer getAmountChildren() {
    return amountChildren;
  }

  public void setAmountChildren(Integer amountChildren) {
    this.amountChildren = amountChildren;
  }

  public EnrollData amountAdults(Integer amountAdults) {
    this.amountAdults = amountAdults;
    return this;
  }

   /**
   * Get amountAdults
   * @return amountAdults
  **/
  @ApiModelProperty(value = "")


  public Integer getAmountAdults() {
    return amountAdults;
  }

  public void setAmountAdults(Integer amountAdults) {
    this.amountAdults = amountAdults;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EnrollData enrollData = (EnrollData) o;
    return Objects.equals(this.userUuid, enrollData.userUuid) &&
        Objects.equals(this.amountChildren, enrollData.amountChildren) &&
        Objects.equals(this.amountAdults, enrollData.amountAdults);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userUuid, amountChildren, amountAdults);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EnrollData {\n");
    
    sb.append("    userUuid: ").append(toIndentedString(userUuid)).append("\n");
    sb.append("    amountChildren: ").append(toIndentedString(amountChildren)).append("\n");
    sb.append("    amountAdults: ").append(toIndentedString(amountAdults)).append("\n");
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

