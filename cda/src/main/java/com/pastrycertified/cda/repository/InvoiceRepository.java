package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Invoice;
import com.pastrycertified.cda.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query(value = "SELECT * FROM invoice INNER JOIN order ON invoice.orders_id = order.id WHERE invoice.orders_id = :id", nativeQuery = true)
    Optional<Invoice>findInvoiceById(Integer id);
    @Query(value = "SELECT * FROM invoice WHERE invoice.invoice_number = :invoice_number", nativeQuery = true)
    Optional<Invoice>findByInvoiceNumber(String invoice_number);
}
