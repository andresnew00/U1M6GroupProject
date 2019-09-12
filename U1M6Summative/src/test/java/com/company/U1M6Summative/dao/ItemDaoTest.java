package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemDaoTest {

    @Autowired
    private ItemDao itemDao;

    private Item item, item2;

    @Before
    public void setUp() throws Exception {
        itemDao.findAll().forEach( item -> {
            itemDao.deleteItem(item.getItemId());
        });

        item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");

        item2 = new Item();
        item2.setDailyRate(new BigDecimal("10.99"));
        item2.setDescription("Test Test");
        item2.setName("Drill");

    }

    @Test
    public void saveItem() {
        item = itemDao.saveItem(item);
        assertEquals(1, itemDao.findAll().size());
    }

    @Test
    public void findOne() {
        item = itemDao.saveItem(item);
        Item testCase = itemDao.findOne(item.getItemId());
        assertEquals(testCase, item);

    }

    @Test
    public void updateItem() {
        item = itemDao.saveItem(item);
        item.setDescription("Value Changed");
        itemDao.updateItem(item);
        Item testCase = itemDao.findOne(item.getItemId());
    }

    @Test
    public void deleteItem() {
        item = itemDao.saveItem(item);
        itemDao.deleteItem(item.getItemId());
        Item testCase = itemDao.findOne(item.getItemId());
        assertNull(testCase);
    }

    @Test
    public void findAll() {

        itemDao.saveItem(item);
        itemDao.saveItem(item2);

        List<Item> items = itemDao.findAll();

        assertEquals(2, items.size());

    }

    @Test
    public void findAllByInvoiceItem() {

        itemDao.saveItem(item);
        itemDao.saveItem(item2);

        List<Item> items = itemDao.findAll();

        assertEquals(2, items.size());
    }
}