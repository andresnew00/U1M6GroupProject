package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Item;

import java.util.List;

public interface ItemDao {
    Item saveItem(Item item);
    Item findOne(int id);
    void updateItem(Item item);
    void deleteItem(int id);
    List<Item> findAll();
    List<Item> findAllByInvoiceItem(int id);
}
