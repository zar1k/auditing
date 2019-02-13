package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Address;
import com.gmail.andreyzarazka.auditing.repository.AddressRepository;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository repository;
    private SessionFactory hibernateFactory;

    public AddressService(AddressRepository repository, EntityManagerFactory factory) {
        this.repository = repository;
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
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

    public Address findRevision(long customerId, int revisionId) {
        AuditReader reader = AuditReaderFactory.get(hibernateFactory.createEntityManager());
        return reader.find(Address.class, customerId, revisionId);
    }

    public List<Number> revisions(long customerId) {
        return AuditReaderFactory.get(hibernateFactory.createEntityManager()).getRevisions(Address.class, customerId);
    }
}
