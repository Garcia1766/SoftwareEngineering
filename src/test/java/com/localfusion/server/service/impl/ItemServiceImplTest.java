package com.localfusion.server.service.impl;

import com.localfusion.server.BootApplication;
import com.localfusion.server.entity.Item;
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
public class ItemServiceImplTest {

    @Autowired
    private ItemServiceImpl itemService;

    @Test
    public void get() {
        Item item = itemService.get(1, null, null, null, null, null, null, null, null, null, null, null).get(0);
        assertNotNull(itemService.get(null, null, null, null, null, null, null, null, null, null, null, null));
        assertNotNull(itemService.get(item.getId(), null, null, null, null, null, null, null, null, null, null, null));
        assertNotNull(itemService.get(null, item.getBusinessId(), null, null, null, null, null, null, null, null, null, null));
        assertNotNull(itemService.get(null, null, item.getName(), null, null, null, null, null, null, null, null, null));
        assertNotNull(itemService.get(null, null, null, item.getPrice(), null, null, null, null, null, null, null, null));
        assertNotNull(itemService.get(null, null, null, null, item.getPicture(), null, null, null, null, null, null, null));
        assertNotNull(itemService.get(null, null, null, null, null, item.getSalesVolume(), null, null, null, null, null, null));
        assertNotNull(itemService.get(null, null, null, null, null, null, item.isDiscounted(), null, null, null, null, null));
        assertNotNull(itemService.get(null, null, null, null, null, null, null, item.getDiscount(), null, null, null, null));
        assertNotNull(itemService.get(null, null, null, null, null, null, null, null, item.isCredited(), null, null, null));
        assertNotNull(itemService.get(null, null, null, null, null, null, null, null, null, item.getHowManyCredits(), null, null));
        assertNotNull(itemService.get(null, null, null, null, null, null, null, null, null, null, item.getHowMuchMoney(), null));
        assertNotNull(itemService.get(null, null, null, null, null, null, null, null, null, null, null, item.isSpecial()));
    }

    @Test
    public void add() {
        Item item = itemService.get(1, null, null, null, null, null, null, null, null, null, null, null).get(0);
        // try to add an item already exists
        assertFalse(itemService.add(new Item().setBusinessId(item.getBusinessId()).setName(item.getName())));
        // add an item that does not exist
        assertTrue(itemService.add(new Item().setBusinessId(item.getBusinessId()).setName(TestUtils.getRandomString())));
        // add an item to a nonexistent business
        assertFalse(itemService.add(new Item().setBusinessId(TestUtils.getBigInt()).setName(TestUtils.getRandomString())));
    }

    @Test
    public void remove() {
        Item item = itemService.get(1, null, null, null, null, null, null, null, null, null, null, null).get(0);
        assertFalse(itemService.remove(item.getId(), item.getBusinessId(), item.getName()));
        assertFalse(itemService.remove(item.getId(), item.getBusinessId(), null));
        assertFalse(itemService.remove(item.getId(), null, item.getName()));
        assertFalse(itemService.remove(TestUtils.getBigInt(), null, null));
        assertTrue(itemService.remove(item.getId(), null, null));

        Item item7 = itemService.get(7, null, null, null, null, null, null, null, null, null, null, null).get(0);
        assertFalse(itemService.remove(null, null, null));
        assertFalse(itemService.remove(null, item7.getBusinessId(), null));
        assertFalse(itemService.remove(null, null, item7.getName()));
        assertFalse(itemService.remove(null, TestUtils.getBigInt(), TestUtils.getRandomString()));
        assertTrue(itemService.remove(null, item7.getBusinessId(), item7.getName()));
    }

    @Test
    public void update() {
        Item item = itemService.get(1, null, null, null, null, null, null, null, null, null, null, null).get(0);
        assertFalse(itemService.update(new Item().setBusinessId(item.getBusinessId()).setId(TestUtils.getBigInt())));
        assertTrue(itemService.update(new Item().setBusinessId(item.getBusinessId()).setId(item.getId())));
        assertFalse(itemService.update(new Item().setBusinessId(TestUtils.getBigInt()).setId(TestUtils.getBigInt())));
    }

    @Test
    public void getSalesVolume() {
        assertEquals(itemService.getSalesVolume(3, "FOR"), -1);
        assertEquals(itemService.getSalesVolume(3, "FOR UNIT TEST"), itemService.getSalesVolume(3, "FOR UNIT TEST"));
    }
}