package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.InvoiceDto;

import java.util.List;

public interface InvoiceService extends AbstractService<InvoiceDto>{

    List<InvoiceDto> findAllByUserId(Integer userId);
}
