package com.gmail.andreyzarazka.auditing.repository;

import com.gmail.andreyzarazka.auditing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}
