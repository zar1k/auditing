package com.gmail.andreyzarazka.auditing.controller;

import com.gmail.andreyzarazka.auditing.model.Bill;
import com.gmail.andreyzarazka.auditing.model.Customer;
import com.gmail.andreyzarazka.auditing.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/find/{id}")
    public Optional<Customer> getById(@PathVariable long id) {
        return this.service.findById(id);
    }

    @GetMapping("/all")
    public Page<Customer> findAll(Pageable pageable) {
        return this.service.findAll(pageable);
    }

    @GetMapping("/bills/{firstName}")
    public Set<Bill> findAllBills(@PathVariable String firstName) {
        return this.service.findAllBills(firstName);
    }

    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        return this.service.save(customer);
    }

    @PutMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return this.service.save(customer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }
}
