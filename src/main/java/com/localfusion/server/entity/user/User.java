package com.localfusion.server.entity.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * user entity
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "user entity")
public class User {

    /**
     * the unique identity for every user in the database
     * dont expose it
     */
    @ApiModelProperty(value = "id")
    private int id;

    /**
     * user properties
     */
    @ApiModelProperty(value = "is user visible")
    private boolean visible;

    @ApiModelProperty(value = "username")
    private String username;

    @ApiModelProperty(value = "password")
    private String password;

    @ApiModelProperty(value = "nickname")
    private String nickname;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "phone")
    private String phone;

    public User() {
        setVisible(true);
    }

}
