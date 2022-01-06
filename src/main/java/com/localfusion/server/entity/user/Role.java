package com.localfusion.server.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "user role entity")
public class Role {

    @ApiModelProperty(value = "id")
    private int id;

    /**
     * pointers to different characters
     */
    @ApiModelProperty(value = "id as administrator")
    private String asAdministrator;

    @ApiModelProperty(value = "id as business")
    private String asBusiness;

    @ApiModelProperty(value = "id as customer")
    private String asCustomer;

}
