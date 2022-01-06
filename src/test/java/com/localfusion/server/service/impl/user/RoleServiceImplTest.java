package com.localfusion.server.service.impl.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@Transactional
@Rollback
public class RoleServiceImplTest {

    @Autowired
    private RoleServiceImpl roleService;

    @Test
    public void register() {
        for (String it : Arrays.asList(UserRole.ADMIN, UserRole.BUSINESS, UserRole.CUSTOMER)) {
            assertTrue(roleService.register(1, it, 1));
            assertTrue(roleService.register(1, it, 3));
            assertTrue(roleService.register(1, it, null));
        }
        assertFalse(roleService.register(1, TestUtils.getRandomString(), TestUtils.getBigInt()));
    }

    @Test
    public void remove() {
        for (String it : Arrays.asList(UserRole.ADMIN, UserRole.BUSINESS, UserRole.CUSTOMER)) {
            assertTrue(roleService.remove(1, it, 1));
            assertTrue(roleService.remove(1, it, 3));
            assertTrue(roleService.remove(1, it, null));
        }
        assertFalse(roleService.remove(1, TestUtils.getRandomString(), TestUtils.getBigInt()));
    }

}
