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
@ApiModel(value = "business entity")
public class Business {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "linked businesses")
    private int link;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "distributedCredit")
    private int distributedCredit;

    @ApiModelProperty(value = "consumedCredit")
    private int consumedCredit;

    @ApiModelProperty(value = "turnover")
    private int turnover;

    @ApiModelProperty(value = "comment")
    private String comment;

}
