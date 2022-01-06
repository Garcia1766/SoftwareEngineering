package com.localfusion.server.controller;

import com.alibaba.fastjson.JSON;
import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.Transaction;
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
public class TransactionControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void get() throws Exception {
        mock.perform(MockMvcRequestBuilders.get(URL.TransactionController.GET).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void submit() throws Exception {
        mock.perform(post(URL.TransactionController.SUBMIT).header(TestUtils.auth, TestUtils.pass)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(new Transaction().setId(1))))
                .andExpect(status().isOk());
    }

}
