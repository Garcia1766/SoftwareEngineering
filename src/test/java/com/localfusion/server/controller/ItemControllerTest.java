package com.localfusion.server.controller;

import com.alibaba.fastjson.JSON;
import com.localfusion.server.BootApplication;
import com.localfusion.server.constant.Table;
import com.localfusion.server.constant.URL;
import com.localfusion.server.entity.Item;
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
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(URL.ItemController.GET).header(TestUtils.auth, TestUtils.pass)
        ).andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        mockMvc.perform(
                post(URL.ItemController.ADD).header(TestUtils.auth, TestUtils.pass)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(new Item()))
        ).andExpect(status().isOk());
    }

    @Test
    public void remove() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(URL.ItemController.REMOVE).header(TestUtils.auth, TestUtils.pass)
                        .param(Table.Item.ID, String.valueOf(1))
                        .param(Table.Item.BUSINESS_ID, String.valueOf(1))
                        .param(Table.Item.NAME, String.valueOf(1))
        ).andExpect(status().isOk());
    }

    @Test
    public void update() throws Exception {
        mockMvc.perform(
                post(URL.ItemController.UPDATE).header(TestUtils.auth, TestUtils.pass)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(new Item().setName(TestUtils.getRandomString())))
        ).andExpect(status().isOk());
    }

    @Test
    public void getSalesVolume() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(URL.ItemController.GET_SALES_VOLUME).header(TestUtils.auth, TestUtils.pass)
                .param(Table.Item.BUSINESS_ID, String.valueOf(1))
                .param(Table.Item.NAME, "FOR UNIT TEST")
        ).andExpect(status().isOk());
    }
}
