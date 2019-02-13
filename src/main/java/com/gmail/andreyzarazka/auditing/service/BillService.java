package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Bill;
import com.gmail.andreyzarazka.auditing.repository.BillRepository;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private final BillRepository repository;
    private SessionFactory hibernateFactory;

    public BillService(BillRepository repository, EntityManagerFactory factory) {
        this.repository = repository;
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
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

    public Bill findRevision(long customerId, int revisionId) {
        AuditReader reader = AuditReaderFactory.get(hibernateFactory.createEntityManager());
        return reader.find(Bill.class, customerId, revisionId);
    }

    public List<Number> revisions(long customerId) {
        return AuditReaderFactory.get(hibernateFactory.createEntityManager()).getRevisions(Bill.class, customerId);
    }
}
