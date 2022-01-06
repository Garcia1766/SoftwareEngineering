package com.localfusion.server.service.impl.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.entity.user.Customer;
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
public class CustomerServiceImplTest {

    @Autowired
    private CustomerServiceImpl customerService;

    @Test
    public void get() {
        Customer customer = customerService.get(1, null, null).get(0);
        assertNotNull(customerService.get(customer.getId(), null, null));
        assertNotNull(customerService.get(null, customer.getLink(), null));
        assertNotNull(customerService.get(null, null, customer.getComment()));
        assertNotNull(customerService.get(null, null, null));
    }

    @Test
    public void update() {
        assertTrue(customerService.update(1, null, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), null, (double)TestUtils.getBigInt(), null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), null, null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)501, null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)2001, null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)5001, null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)20001, null));
        assertTrue(customerService.update(1, (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)100001, null));
        assertFalse(customerService.update(TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), (double)TestUtils.getBigInt(), null));
    }

}
