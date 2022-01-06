package com.localfusion.server.service.impl.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.entity.user.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@Transactional
@Rollback
public class AdminServiceImplTest {

    @Autowired
    private AdminServiceImpl adminService;

    @Test
    public void get() {
        Admin admin = adminService.get(1, null).get(0);
        assertNotNull(adminService.get(admin.getId(), null));
        assertNotNull(adminService.get(null, admin.getLink()));
        assertNotNull(adminService.get(null, null));
    }

    @Test
    public void getRule() {
        assertNotNull(adminService.getRule());
    }

    @Test
    public void setRequiredCreditsPerLevel() {
        assertTrue(adminService.setRequiredCreditsPerLevel(100, 200, 300, 400, 500));
    }

    @Test
    public void setDiscount() {
        assertTrue(adminService.setDiscount(0.97, 0.95, 0.93, 0.91, 0.89, 0.87));
    }

    @Test
    public void setCreditsPer100Yuan() {
        assertTrue(adminService.setCreditsPer100Yuan(100));
    }
}