package com.localfusion.server.service.impl;

import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.UserRole;
import com.localfusion.server.entity.Transaction;
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
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@Transactional
@Rollback
public class TransactionServiceImplTest {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Test
    public void get() {
        Transaction transaction = transactionService.get(1, null, null, null, null).get(0);
        assertNotNull(transactionService.get(transaction.getId(), null, null, null, null));
        assertNotNull(transactionService.get(null, transaction.getTransferor(), transaction.getTransferorRole(), null, null));
        assertNotNull(transactionService.get(null, null, null, transaction.getTransferee(), transaction.getTransfereeRole()));
        assertNotNull(transactionService.get(null, null, null, null, null));
    }

    @Test
    public void submit() {
        assertFalse(transactionService.submit(new Transaction().setId(1)));

        assertFalse(transactionService.submit(new Transaction()
                .setTransferor(TestUtils.getBigInt()).setTransferorRole(TestUtils.getRandomString())
                .setTransferee(TestUtils.getBigInt()).setTransfereeRole(TestUtils.getRandomString())));
        assertFalse(transactionService.submit(new Transaction()
                .setTransferor(TestUtils.getBigInt()).setTransferorRole(UserRole.BUSINESS)
                .setTransferee(1).setTransfereeRole(UserRole.CUSTOMER)));
        assertFalse(transactionService.submit(new Transaction()
                .setTransferor(1).setTransferorRole(UserRole.BUSINESS)
                .setTransferee(TestUtils.getBigInt()).setTransfereeRole(UserRole.CUSTOMER)));

        for (String it : Arrays.asList(UserRole.BUSINESS, UserRole.CUSTOMER)) {
            assertTrue(transactionService.submit(new Transaction()
                    .setTransferor(1).setTransferorRole(it)
                    .setTransferee(1).setTransfereeRole(it)
                    .setItemIdList("1,7")
                    .setTime(new Date())));
            assertTrue(transactionService.submit(new Transaction()
                    .setTransferor(1).setTransferorRole(it)
                    .setTransferee(1).setTransfereeRole(it)
                    .setItemIdList(",,1," + "")
                    .setTime(new Date())));
        }
    }

}
