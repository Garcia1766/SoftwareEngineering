package com.localfusion.server.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * customer entity
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "customer entity")
public class Customer {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "linked users")
    private int link;

    @ApiModelProperty(value = "level")
    private int level;

    @ApiModelProperty(value = "credit")
    private double credit;

    @ApiModelProperty(value = "balance")
    private double balance;

    @ApiModelProperty(value = "comment")
    private String comment;

    @ApiModelProperty(value = "exp")
    private double exp;

}
