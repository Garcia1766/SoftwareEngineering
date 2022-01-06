package com.localfusion.server.service.impl.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.entity.user.Business;
import com.localfusion.server.service.impl.RuleServiceImpl;
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
public class BusinessServiceImplTest {

    @Autowired
    private BusinessServiceImpl businessService;

    @Autowired
    private RuleServiceImpl ruleService;

    @Test
    public void get(){
        Business business = businessService.get(1, null, null, null).get(0);
        assertNotNull(businessService.get(business.getId(), null, null, null));
        assertNotNull(businessService.get(null, business.getLink(), null, null));
        assertNotNull(businessService.get(null, null, business.getName(), null));
        assertNotNull(businessService.get(null, null, null, business.getComment()));
        assertNotNull(businessService.get(null, null, null, null));
    }

    @Test
    public void distributeCredit() {
        Business business = businessService.get(1, null, null, null).get(0);
        int delta, before, after;

        before = business.getDistributedCredit();
        delta = TestUtils.getBigInt();
        assertTrue(businessService.distributeCredit(business.getId(), delta));
        business = businessService.getById(business.getId());  // update local variable business
        after = business.getDistributedCredit();
        assertEquals(before + delta, after);
    }

    @Test
    public void consumeCredit() {
        Business business = businessService.get(1, null, null, null).get(0);
        int delta, before, after;

        before = business.getConsumedCredit();
        delta = TestUtils.getBigInt();
        assertTrue(businessService.consumeCredit(business.getId(), delta));
        business = businessService.getById(business.getId());  // update local variable business
        after = business.getConsumedCredit();
        assertEquals(before + delta, after);
    }

    @Test
    public void increaseTurnover() {
        Business business = businessService.get(1, null, null, null).get(0);
        int delta, before, after;

        before = business.getTurnover();
        delta = TestUtils.getBigInt();
        assertTrue(businessService.increaseTurnover(business.getId(), delta));
        business = businessService.getById(business.getId());
        after = business.getTurnover();
        assertEquals(before + delta, after);

        assertFalse(businessService.increaseTurnover(TestUtils.getBigInt(), TestUtils.getBigInt()));
    }

    @Test
    public void settleCredit() {
        Business business = businessService.get(1, null, null, null).get(0);

        assertEquals(business.getConsumedCredit() - business.getDistributedCredit() < 0 ? 0 :
                (business.getConsumedCredit() - business.getDistributedCredit()) / ruleService.get(1).get(0).getCreditsPer100Yuan() * 100,
                businessService.settleCredit(business.getId()));

        assertEquals(-1, businessService.settleCredit(TestUtils.getBigInt()));
    }
}
