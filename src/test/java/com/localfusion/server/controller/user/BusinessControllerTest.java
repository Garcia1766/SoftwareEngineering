package com.localfusion.server.controller.user;

import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.URL;
import com.localfusion.server.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
@Transactional
@Rollback
public class BusinessControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.GET).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void getNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.GET_NUMBER).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void distributeCredit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.DISTRIBUTE_CREDIT).header(TestUtils.auth, TestUtils.pass)
                .param("id", String.valueOf(TestUtils.getBigInt()))
                .param("delta", String.valueOf(TestUtils.getBigInt())))
                .andExpect(status().isOk());
    }

    @Test
    public void consumeCredit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.CONSUME_CREDIT).header(TestUtils.auth, TestUtils.pass)
                .param("id", String.valueOf(TestUtils.getBigInt()))
                .param("delta", String.valueOf(TestUtils.getBigInt())))
                .andExpect(status().isOk());
    }

    @Test
    public void increaseTurnover() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.INCREASE_TURNOVER).header(TestUtils.auth, TestUtils.pass)
                .param("id", String.valueOf(TestUtils.getBigInt()))
                .param("money", String.valueOf(TestUtils.getBigInt()))
        ).andExpect(status().isOk());
    }

    @Test
    public void settleCredit() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.BusinessController.SETTLE_CREDIT).header(TestUtils.auth, TestUtils.pass)
                .param("id", String.valueOf(TestUtils.getBigInt()))
        ).andExpect(status().isOk());
    }
}