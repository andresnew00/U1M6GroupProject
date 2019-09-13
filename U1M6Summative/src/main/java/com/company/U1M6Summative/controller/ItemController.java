package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dto.Item;
import com.company.U1M6Summative.dto.Item;
import com.company.U1M6Summative.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/Item")
public class ItemController {

    @Autowired
    ServiceLayer service;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Item addItem(@RequestBody @Valid Item item) {
        return service.saveItem(item);

    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Item> getAllItems() {
        if (service.getAllItems().size() > 0) {
            return service.getAllItems();
        } else {
            throw new IllegalArgumentException("Items not found.");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Item getItem(@PathVariable int id) {
        return service.findItem(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateItem(@PathVariable int id, @RequestBody @Valid Item updated) {
        updated.setItemId(id);
        service.updateItem(updated);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCity(@PathVariable int id) {
        if (service.getAllItems().contains(service.findItem(id))) {
            service.deleteItem(id);
        } else {
            throw new IllegalArgumentException("Item ID not Found");

        }
    }
}
