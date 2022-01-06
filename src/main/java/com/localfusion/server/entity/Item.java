package com.localfusion.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * item entity
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "item entity")
public class Item {

    // basic information
    @ApiModelProperty
    private int id;

    @ApiModelProperty
    private int businessId;

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private double price;

    @ApiModelProperty
    private String picture;

    @ApiModelProperty
    private int salesVolume;

    // sale strategy
    @ApiModelProperty
    private boolean isDiscounted;

    @ApiModelProperty
    private double discount;  // 0 < discount < 1

    @ApiModelProperty
    private boolean isCredited;

    @ApiModelProperty
    private int howManyCredits;

    @ApiModelProperty
    private int howMuchMoney;

    @ApiModelProperty
    private boolean special;

    @ApiModelProperty
    private double specialPrice;

    @ApiModelProperty
    private Date startDate;

    @ApiModelProperty
    private Date endDate;

}
