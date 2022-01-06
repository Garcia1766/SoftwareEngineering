package com.localfusion.server.service.impl.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.user.User;
import com.localfusion.server.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@Transactional
@Rollback
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void get() {
        User user = userService.get(1, null, null, null, null, null).get(0);
        assertNotNull(userService.get(user.getId(), null, null, null, null, null));
        assertNotNull(userService.get(null, user.getUsername(), null, null, null, null));
        assertNotNull(userService.get(null, null, user.getPassword(), null, null, null));
        assertNotNull(userService.get(null, null, null, user.getNickname(), null, null));
        assertNotNull(userService.get(null, null, null, null, user.getEmail(), null));
        assertNotNull(userService.get(null, null, null, null, null, user.getPhone()));
        assertNotNull(userService.get(null, null, null, null, null, null));
    }

    @Test
    public void getRole() {
        assertTrue(userService.getRole(TestUtils.getBigInt(), TestUtils.getRandomString()).isEmpty());
        assertFalse(userService.getRole(1, UserRole.ADMIN).isEmpty());
        assertFalse(userService.getRole(1, UserRole.BUSINESS).isEmpty());
        assertFalse(userService.getRole(1, UserRole.CUSTOMER).isEmpty());
        assertTrue(userService.getRole(1, TestUtils.getRandomString()).isEmpty());
    }

    @Test
    public void register() {
        assertTrue(userService.register(new User().setUsername(TestUtils.getRandomString())));
        assertFalse(userService.register(new User().setUsername(userService.get(1, null, null, null, null, null).get(0).getUsername())));
    }

    @Test
    public void remove() {
        assertTrue(userService.remove(1));
    }

    @Test
    public void update() {
        assertTrue(userService.update(userService.get(1, null, null, null, null, null).get(0).setUsername(TestUtils.getRandomString())));
        assertFalse(userService.update(new User()));
    }

}
