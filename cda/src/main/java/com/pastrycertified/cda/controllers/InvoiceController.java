package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.InvoiceDto;
import com.pastrycertified.cda.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
