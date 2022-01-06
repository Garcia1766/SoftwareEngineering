package com.localfusion.server.controller.user;

import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.user.User;
import com.localfusion.server.service.impl.user.RoleServiceImpl;
import com.localfusion.server.service.impl.user.UserServiceImpl;
import com.localfusion.server.service.user.IRoleService;
import com.localfusion.server.service.user.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * user controller
 */
@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private IUserService userService;
    private IRoleService roleService;

    public UserController(final UserServiceImpl userService, final RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = URL.UserController.EXIST)
    public boolean exist(@RequestParam(name = Table.User.ID, required = false) final Integer id,
                         @RequestParam(name = Table.User.USERNAME, required = false) final String username,
                         @RequestParam(name = Table.User.PASSWORD, required = false) final String password,
                         @RequestParam(name = Table.User.NICKNAME, required = false) final String nickname,
                         @RequestParam(name = Table.User.EMAIL, required = false) final String email,
                         @RequestParam(name = Table.User.PHONE, required = false) final String phone) {
        return !userService.get(id, username, password, nickname, email, phone).isEmpty();
    }

    @GetMapping(value = URL.UserController.GET)
    public List<User> get(@RequestParam(name = Table.User.ID, required = false) final Integer id,
                          @RequestParam(name = Table.User.USERNAME, required = false) final String username,
                          @RequestParam(name = Table.User.PASSWORD, required = false) final String password,
                          @RequestParam(name = Table.User.NICKNAME, required = false) final String nickname,
                          @RequestParam(name = Table.User.EMAIL, required = false) final String email,
                          @RequestParam(name = Table.User.PHONE, required = false) final String phone) {
        return userService.get(id, username, password, nickname, email, phone);
    }

    @GetMapping(value = URL.UserController.GET_ROLE)
    public List<String> getRole(@RequestParam(name = Table.User.ID) final int id,
                                @RequestParam(name = "role") final String role) {
        return userService.getRole(id, role);
    }

    @PostMapping(value = URL.UserController.REGISTER)
    public boolean register(@RequestBody final User user) {
        return userService.register(user);
    }

    @GetMapping(value = URL.UserController.REGISTER_ROLE)
    public boolean registerRole(@RequestParam(name = "userId") final int userId,
                                @RequestParam(name = "role") final String role,
                                @RequestParam(name = "roleId", required = false) final Integer roleId) {
        return roleService.register(userId, role, roleId);
    }

    @GetMapping(value = URL.UserController.REMOVE)
    public boolean remove(@RequestParam(name = Table.User.ID) final int id) {
        return userService.remove(id);
    }

    @GetMapping(value = URL.UserController.REMOVE_ROLE)
    public boolean removeRole(@RequestParam(name = Table.User.ID) final int id,
                              @RequestParam(name = "role", required = false) final String role,
                              @RequestParam(name = "roleId", required = false) final Integer roleId) {
        return roleService.remove(id, role, roleId);
    }

    @PostMapping(value = URL.UserController.UPDATE)
    public boolean update(@RequestBody final User user) {
        return userService.update(user);
    }

    @GetMapping(value = URL.UserController.WX_LOGIN)
    public String wxLogin(@RequestParam(name = "code") final String code) {
        return new RestTemplate().getForObject(URL.UserController.wxLoginApi(code), String.class);
    }

}
