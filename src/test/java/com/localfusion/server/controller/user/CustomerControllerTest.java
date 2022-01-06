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
public class CustomerControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void get() throws Exception {
        mock.perform(MockMvcRequestBuilders.get(URL.CustomerController.GET).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void getNumber() throws Exception {
        mock.perform(MockMvcRequestBuilders.get(URL.CustomerController.GET_NUMBER).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        mock.perform(MockMvcRequestBuilders.get(URL.CustomerController.UPDATE).header(TestUtils.auth, TestUtils.pass)
                .param("id", String.valueOf(TestUtils.getBigInt()))
                .param("creditDelta", String.valueOf(TestUtils.getBigInt()))
                .param("balanceDelta", String.valueOf(TestUtils.getBigInt()))
                .param("expDelta", String.valueOf(TestUtils.getBigInt()))
                .param("comment", "")
        ).andExpect(status().isOk());
    }

}
