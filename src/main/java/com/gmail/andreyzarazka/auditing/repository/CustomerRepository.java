package com.gmail.andreyzarazka.auditing.repository;

import com.gmail.andreyzarazka.auditing.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByFirstName(String firstName);
}
