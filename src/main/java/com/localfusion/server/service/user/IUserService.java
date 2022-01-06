package com.localfusion.server.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.localfusion.server.entity.user.User;

import java.util.List;

/**
 * user service interface
 */
public interface IUserService extends IService<User> {

    List<User> get(final Integer id,
                   final String username,
                   final String password,
                   final String nickname,
                   final String email,
                   final String phone);

    List<String> getRole(final int id, final String role);

    boolean register(final User user);

    boolean remove(final int id);

    boolean update(final User user);

}
