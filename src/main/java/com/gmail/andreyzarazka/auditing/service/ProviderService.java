package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Provider;
import com.gmail.andreyzarazka.auditing.repository.ProviderRepository;
import org.hibernate.SessionFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {
    private final ProviderRepository repository;
    private SessionFactory hibernateFactory;

    public ProviderService(ProviderRepository repository, EntityManagerFactory factory) {
        this.repository = repository;
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    public Provider save(Provider services) {
        return this.repository.saveAndFlush(services);
    }

    public Optional<Provider> findById(long id) {
        return this.repository.findById(id);
    }

    public Page<Provider> findAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }

    public void delete(long id) {
        this.repository.deleteById(id);
    }

    public Provider findRevision(long customerId, int revisionId) {
        AuditReader reader = AuditReaderFactory.get(hibernateFactory.createEntityManager());
        return reader.find(Provider.class, customerId, revisionId);
    }

    public List<Number> revisions(long customerId) {
        return AuditReaderFactory.get(hibernateFactory.createEntityManager()).getRevisions(Provider.class, customerId);
    }
}
