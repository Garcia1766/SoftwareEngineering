package com.localfusion.server.service.impl.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.user.Role;
import com.localfusion.server.entity.user.User;
import com.localfusion.server.mapper.user.UserMapper;
import com.localfusion.server.service.user.IRoleService;
import com.localfusion.server.service.user.IUserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * user service implementation
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private IRoleService roleService;

    public UserServiceImpl(final RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<User> get(final Integer id,
                          final String username,
                          final String password,
                          final String nickname,
                          final String email,
                          final String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq(Table.User.VISIBLE, true);
        if (id != null) {
            queryWrapper = queryWrapper.eq(Table.User.ID, id);
        }
        if (username != null) {
            queryWrapper = queryWrapper.eq(Table.User.USERNAME, username);
        }
        if (password != null) {
            queryWrapper = queryWrapper.eq(Table.User.PASSWORD, password);
        }
        if (nickname != null) {
            queryWrapper = queryWrapper.eq(Table.User.NICKNAME, nickname);
        }
        if (email != null) {
            queryWrapper = queryWrapper.eq(Table.User.EMAIL, email);
        }
        if (phone != null) {
            queryWrapper = queryWrapper.eq(Table.User.PHONE, phone);
        }
        return list(queryWrapper);
    }

    @Override
    public List<String> getRole(final int id, final String role) {
        if (!get(id, null, null, null, null, null).isEmpty()) {
            Role userRole = roleService.getById(id);
            switch (role) {
                case UserRole.ADMIN:
                    return Arrays.asList(userRole.getAsAdministrator().split(","));
                case UserRole.BUSINESS:
                    return Arrays.asList(userRole.getAsBusiness().split(","));
                case UserRole.CUSTOMER:
                    return Arrays.asList(userRole.getAsCustomer().split(","));
                default:
                    return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean register(final User user) {
        if (get(null, user.getUsername(), null, null, null, null).isEmpty()) {
            save(user);
            User savedUser = get(null, user.getUsername(), null, null, null, null).get(0);
            roleService.save(new Role().setId(savedUser.getId()));
            // every user will be assigned with a customer account when registered
            roleService.register(savedUser.getId(), UserRole.CUSTOMER, null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(final int id) {
        update(new User().setId(id).setVisible(false));
        roleService.remove(id, null, null);
        return true;
    }

    @Override
    public boolean update(final User user) {
        if (get(user.getId(), null, null, null, null, null).isEmpty()) {
            return false;
        } else {
            return updateById(user);
        }
    }

}
