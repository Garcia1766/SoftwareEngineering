package com.localfusion.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * transaction entity
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "transaction entity")
public class Transaction {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "transferor")
    private int transferor;

    @ApiModelProperty(value = "transferor role")
    private String transferorRole;

    @ApiModelProperty(value = "transferee")
    private int transferee;

    @ApiModelProperty(value = "transferee role")
    private String transfereeRole;

    @ApiModelProperty(value = "time")
    private Date time;

    @ApiModelProperty(value = "credit")
    private double credit;

    @ApiModelProperty(value = "money")
    private double money;

    @ApiModelProperty(value = "item id list")
    private String itemIdList;

    @ApiModelProperty(value = "comment")
    private String comment;

    @ApiModelProperty(value = "exp")
    private double exp;

}
