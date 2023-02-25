package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.InvoiceDto;
import com.pastrycertified.cda.models.Invoice;
import com.pastrycertified.cda.models.Options;

public interface InvoiceService extends AbstractService<InvoiceDto>{
    Invoice save(Options dto);
}
