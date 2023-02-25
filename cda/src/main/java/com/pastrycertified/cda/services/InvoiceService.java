package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.InvoiceDto;
import com.pastrycertified.cda.models.Invoice;
import com.pastrycertified.cda.models.Options;

import java.util.List;

public interface InvoiceService extends AbstractService<InvoiceDto>{
    Invoice save(Options dto);

    List<InvoiceDto> findAllByUserId(Integer userId);
}