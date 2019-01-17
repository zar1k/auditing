package com.gmail.andreyzarazka.auditing.repository;

import com.gmail.andreyzarazka.auditing.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
