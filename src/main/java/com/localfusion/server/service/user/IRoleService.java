package com.localfusion.server.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.user.Role;

/**
 * user role service interface
 */
public interface IRoleService extends IService<Role> {

    boolean register(final int userId, final String role, final Integer roleId);

    boolean remove(final int userId, final String role, final Integer roleId);

}
