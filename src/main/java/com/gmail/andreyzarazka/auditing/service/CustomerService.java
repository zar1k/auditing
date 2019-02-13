package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Bill;
import com.gmail.andreyzarazka.auditing.model.Customer;
import com.gmail.andreyzarazka.auditing.repository.CustomerRepository;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private SessionFactory hibernateFactory;

    public CustomerService(CustomerRepository repository, EntityManagerFactory factory) {
        this.repository = repository;
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
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

    public Customer findRevision(long customerId, int revisionId) {
        AuditReader reader = AuditReaderFactory.get(hibernateFactory.createEntityManager());
        return reader.find(Customer.class, customerId, revisionId);
    }

    public List<Number> revisions(long customerId) {
        return AuditReaderFactory.get(hibernateFactory.createEntityManager()).getRevisions(Customer.class, customerId);
    }
}
