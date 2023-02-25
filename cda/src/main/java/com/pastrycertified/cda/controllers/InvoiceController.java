package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.InvoiceDto;
import com.pastrycertified.cda.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.invoice}")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("${create.invoice}")
    public ResponseEntity<Integer>save(
            @RequestBody InvoiceDto invoiceDto
            ) {
        return ResponseEntity.ok(invoiceService.save(invoiceDto));
    }

    @GetMapping("${all.invoices}")
    public ResponseEntity<List<InvoiceDto>>findAll() { return ResponseEntity.ok(invoiceService.findAll());}

    @GetMapping("${invoice.invoiceId}")
    public ResponseEntity<InvoiceDto>findById(
            @PathVariable("invoice-id") Integer invoiceId
    ) {
        return ResponseEntity.ok(invoiceService.findById(invoiceId));
    }

    @GetMapping("${all.invoices.userId}")
    public ResponseEntity<List<InvoiceDto>>findAllByUserId(
            @PathVariable("user-id") Integer userId
    ) { return ResponseEntity.ok(invoiceService.findAllByUserId(userId));}


    @DeleteMapping("${delete.invoice.invoiceId}")
    public ResponseEntity<Void> delete(
            @PathVariable("invoice-id") Integer invoiceId
    ) {
        invoiceService.delete(invoiceId);
        return ResponseEntity.accepted().build();
    }
}
