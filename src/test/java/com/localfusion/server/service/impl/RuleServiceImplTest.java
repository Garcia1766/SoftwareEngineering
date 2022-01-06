package com.localfusion.server.service.impl;

import com.localfusion.server.BootApplication;
import com.localfusion.server.entity.Rule;
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
public class RuleServiceImplTest {

    @Autowired
    private RuleServiceImpl ruleService;

    @Test
    public void get() {
        Rule rule = ruleService.get(1).get(0);
        assertNotNull(ruleService.get(rule.getId()));
        assertNotNull(ruleService.get(null));
    }

    @Test
    public void update() {
        Rule rule = ruleService.get(1).get(0);
        assertFalse(ruleService.update(new Rule().setId(TestUtils.getBigInt())));
        assertTrue(ruleService.update(new Rule().setId(rule.getId())));
    }
}