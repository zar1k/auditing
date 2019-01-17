package com.gmail.andreyzarazka.auditing.service;

import com.gmail.andreyzarazka.auditing.model.Provider;
import com.gmail.andreyzarazka.auditing.repository.ProviderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {
    private final ProviderRepository repository;

    public ProviderService(ProviderRepository repository) {
        this.repository = repository;
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
}
