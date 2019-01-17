package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Address;
import com.gmail.andreyzarazka.auditing.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public Address save(Address address) {
        return this.repository.saveAndFlush(address);
    }

    public Optional<Address> findById(long id) {
        return this.repository.findById(id);
    }

    public Iterable<Address> findAll() {
        return this.repository.findAll();
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }
}
