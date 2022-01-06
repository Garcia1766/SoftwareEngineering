package com.localfusion.server.controller;

import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootApplication.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class UtilControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
    public void itemQR() throws Exception {
        mock.perform(get(URL.UtilController.ITEM).header(TestUtils.auth, TestUtils.pass)
                .param(Table.Item.ID, String.valueOf(TestUtils.getBigInt())))
                .andExpect(status().is3xxRedirection());
    }

}
