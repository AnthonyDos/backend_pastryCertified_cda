package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.InvoiceDto;
import com.pastrycertified.cda.models.*;
import com.pastrycertified.cda.repository.InvoiceRepository;
import com.pastrycertified.cda.services.InvoiceService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ObjectsValidator validator;

    @Override
    public Integer save(InvoiceDto dto) {
        validator.validate(dto);
        String invoiceNumber = generateInvoiceCharacters(3,4);

        Invoice invoice = InvoiceDto.toEntity(dto);
        invoice.setInvoice_number(controlInvoiceNumberIfExist(invoiceNumber));
        invoice.setDetails(dto.getDetails());
        invoice.setOrders(
                Orders.builder()
                        .id(dto.getOrderId())
                        .build()
        );

        return invoiceRepository.save(invoice).getId();
    }


    @Override
    public List<InvoiceDto> findAll() {
        return null;
    }

    @Override
    public InvoiceDto findById(Integer id) {
        return null;
    }

    @Override
    public Integer updateByid(Integer id, InvoiceDto dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    private String controlInvoiceNumberIfExist(String invoiceNumber) {
        Invoice invoice = invoiceRepository.findByInvoiceNumber(invoiceNumber)
                .orElse(null);
        if (invoice != null) {
            return  generateInvoiceCharacters(1, 4);
        }

        return invoiceNumber;
    }
    public static String generateInvoiceCharacters(int length, int lengthNumber) {
        String characters = GenerateCharactersInvoice.CHARACTERS.getValue();
        String numbers = GenerateCharactersInvoice.NUMBERS.getValue();

        StringBuffer randomString = new StringBuffer(length);
        StringBuffer randomNumber = new StringBuffer(lengthNumber);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            randomString.append(randomChar);
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(numbers.length());
            char randomChar = numbers.charAt(randomIndex);
            randomNumber.append(randomChar);
        }
        return randomString.toString().toUpperCase() + randomNumber.toString();
    }

    @Override
    public Invoice save(Options dto) {
        return null;
    }
}
