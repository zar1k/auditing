package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Bill;
import com.gmail.andreyzarazka.auditing.model.Customer;
import com.gmail.andreyzarazka.auditing.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer save(Customer customer) {
        return this.repository.saveAndFlush(customer);
    }

    public Optional<Customer> findById(long id) {
        return this.repository.findById(id);
    }

    public Page<Customer> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public Set<Bill> findAllBills(String firstName) {
        Customer customer = this.repository.findCustomerByFirstName(firstName);
        if (customer != null) {
            return customer.getBills();
        }
        return Collections.emptySet();
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }
}
