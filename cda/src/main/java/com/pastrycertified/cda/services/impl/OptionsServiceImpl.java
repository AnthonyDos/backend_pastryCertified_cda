package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.OptionsDto;
import com.pastrycertified.cda.models.Options;
import com.pastrycertified.cda.repository.OptionsRepository;
import com.pastrycertified.cda.services.OptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OptionsServiceImpl implements OptionsService {

    private final OptionsRepository optionsRepository;

    @Override
    public Options save(OptionsDto dto) {
        System.out.println(dto + " dto");
        Options option = OptionsDto.toEntity(dto);
        option.setTypeOption(option.getTypeOption());
        option.setListOption(option.getListOption());
        //option.setCategory(option.getCategory());
        return optionsRepository.save(option);
    }

    @Override
    public List<OptionsDto> findAll() {
        return optionsRepository.findAll()
                .stream()
                .map(OptionsDto::fromEntity)
                .collect(Collectors.toList());
    }


}
