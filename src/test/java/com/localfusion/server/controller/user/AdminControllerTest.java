package com.localfusion.server.controller.user;

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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.GET).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void getNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.GET_NUMBER).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void getRule() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.GET_RULE).header(TestUtils.auth, TestUtils.pass))
                .andExpect(status().isOk());
    }

    @Test
    public void setRequiredCreditsPerLevel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.SET_REQUIRED_CREDITS_PER_LEVEL).header(TestUtils.auth, TestUtils.pass)
                .param(Table.Rule.FIRST_TO_SECOND, String.valueOf(TestUtils.getBigInt()))
                .param(Table.Rule.SECOND_TO_THIRD, String.valueOf(TestUtils.getBigInt()))
                .param(Table.Rule.THIRD_TO_FOURTH, String.valueOf(TestUtils.getBigInt()))
                .param(Table.Rule.FOURTH_TO_FIFTH, String.valueOf(TestUtils.getBigInt()))
                .param(Table.Rule.FIFTH_TO_SIXTH, String.valueOf(TestUtils.getBigInt()))
        ).andExpect(status().isOk());
    }

    @Test
    public void setDiscount() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.SET_DISCOUNT).header(TestUtils.auth, TestUtils.pass)
                .param(Table.Rule.DISCOUNT_LEVEL1, String.valueOf((double)TestUtils.getBigInt()))
                .param(Table.Rule.DISCOUNT_LEVEL2, String.valueOf((double)TestUtils.getBigInt()))
                .param(Table.Rule.DISCOUNT_LEVEL3, String.valueOf((double)TestUtils.getBigInt()))
                .param(Table.Rule.DISCOUNT_LEVEL4, String.valueOf((double)TestUtils.getBigInt()))
                .param(Table.Rule.DISCOUNT_LEVEL5, String.valueOf((double)TestUtils.getBigInt()))
                .param(Table.Rule.DISCOUNT_LEVEL6, String.valueOf((double)TestUtils.getBigInt()))
        ).andExpect(status().isOk());
    }

    @Test
    public void setCreditsPer100Yuan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL.AdminController.SET_CREDITS_PER100_YUAN).header(TestUtils.auth, TestUtils.pass)
                .param(Table.Rule.CREDITS_PER100_YUAN, String.valueOf(TestUtils.getBigInt()))
        ).andExpect(status().isOk());
    }
}
