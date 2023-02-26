package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.InvoiceDto;

import java.util.List;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface InvoiceService extends AbstractService<InvoiceDto>{

    List<InvoiceDto> findAllByUserId(Integer userId);
}
