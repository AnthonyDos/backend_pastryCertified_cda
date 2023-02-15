package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.OptionsDto;
import com.pastrycertified.cda.models.Options;

import java.util.List;

public interface OptionsService {

    Options save(OptionsDto dto);

    List<OptionsDto> findAll();

    OptionsDto findByName(String typeOption);

    void delete(Integer id);
}
