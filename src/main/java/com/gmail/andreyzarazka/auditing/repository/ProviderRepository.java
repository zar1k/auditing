package com.gmail.andreyzarazka.auditing.repository;

import com.gmail.andreyzarazka.auditing.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

}