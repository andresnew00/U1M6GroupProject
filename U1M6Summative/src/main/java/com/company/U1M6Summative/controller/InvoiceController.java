package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/Invoice")
@RestController
public class InvoiceController {

    @Autowired
    ServiceLayer service;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCity(@PathVariable int id) {
        if (service.findAllInvoices().contains(service.findInvoice(id))) {
            service.deleteInvoice(id);
        } else {
            throw new IllegalArgumentException("Invoice ID not Found");

        }

    }
        @RequestMapping(method = RequestMethod.POST)
        @ResponseStatus(value = HttpStatus.CREATED)
        public Invoice addInvoice(@RequestBody Invoice invoice) {
            return service.saveInvoice(invoice);

        }

}
