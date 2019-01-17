package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Bill;
import com.gmail.andreyzarazka.auditing.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService {
    private final BillRepository repository;

    public BillService(BillRepository repository) {
        this.repository = repository;
    }

    public Bill save(Bill bill) {
        return this.repository.saveAndFlush(bill);
    }

    public Optional<Bill> findById(long id) {
        return this.repository.findById(id);
    }

    public Iterable<Bill> findAll() {
        return this.repository.findAll();
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }
}
