package com.localfusion.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Rule entity
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "rule entity")
public class Rule {

    @ApiModelProperty
    private int id;

    // required credits per level
    @ApiModelProperty
    public int firstToSecond;
    @ApiModelProperty
    public int secondToThird;
    @ApiModelProperty
    public int thirdToFourth;
    @ApiModelProperty
    public int fourthToFifth;
    @ApiModelProperty
    public int fifthToSixth;

    // discount privileges for each level, (0, 1]
    @ApiModelProperty
    public double discountLevel1;
    @ApiModelProperty
    public double discountLevel2;
    @ApiModelProperty
    public double discountLevel3;
    @ApiModelProperty
    public double discountLevel4;
    @ApiModelProperty
    public double discountLevel5;
    @ApiModelProperty
    public double discountLevel6;

    // the percentage of credits obtained through consumption
    @ApiModelProperty
    public int creditsPer100Yuan;
}
