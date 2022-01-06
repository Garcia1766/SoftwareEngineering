package com.localfusion.server.controller.user;

import com.alibaba.fastjson.JSON;
import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.user.User;
import com.localfusion.server.service.impl.user.UserServiceImpl;
import com.localfusion.server.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@Transactional
@Rollback
public class UserControllerTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MockMvc mock;

    @Test
    public void exist() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.EXIST).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.EXIST).header(TestUtils.auth, TestUtils.pass)
                        .param(Table.User.ID, String.valueOf(TestUtils.getBigInt())))
                .andExpect(status().isOk());
    }

    @Test
    public void get() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.GET).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void getRole() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.GET_ROLE).header(TestUtils.auth, TestUtils.pass)
                        .param(Table.User.ID, "1")
                        .param("role", UserRole.CUSTOMER))
                .andExpect(status().isOk());
    }

    @Test
    public void register() throws Exception {
        mock.perform(
                post(URL.UserController.REGISTER).header(TestUtils.auth, TestUtils.pass)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(new User())))
                .andExpect(status().isOk());
    }

    @Test
    public void registerRole() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.REGISTER_ROLE).header(TestUtils.auth, TestUtils.pass)
                        .param("userId", String.valueOf(TestUtils.getBigInt()))
                        .param("role", TestUtils.getRandomString())
                        .param("roleId", String.valueOf(TestUtils.getBigInt())))
                .andExpect(status().isOk());
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.REGISTER_ROLE).header(TestUtils.auth, TestUtils.pass)
                        .param("userId", String.valueOf(TestUtils.getBigInt()))
                        .param("role", TestUtils.getRandomString()))
                .andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.REMOVE).header(TestUtils.auth, TestUtils.pass)
                        .param(Table.User.ID, "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void removeRole() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.REMOVE_ROLE).header(TestUtils.auth, TestUtils.pass)
                        .param(Table.User.ID, "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        mock.perform(
                post(URL.UserController.UPDATE).header(TestUtils.auth, TestUtils.pass)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(userService.get(1, null, null, null, null, null).get(0).setUsername(TestUtils.getRandomString()))))
                .andExpect(status().isOk());
    }

    @Test
    public void wxLogin() throws Exception {
        mock.perform(
                MockMvcRequestBuilders.get(URL.UserController.WX_LOGIN).header(TestUtils.auth, TestUtils.pass)
                        .param("code", TestUtils.getRandomString()))
                .andExpect(status().isOk());
    }

}
