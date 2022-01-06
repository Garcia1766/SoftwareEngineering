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
@ApiModel(value = "admin entity")
public class Admin {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "linked administrators")
    private int link;

}
