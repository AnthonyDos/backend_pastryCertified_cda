package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.OptionsDto;
import com.pastrycertified.cda.models.Options;
import com.pastrycertified.cda.services.OptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.options}")
@RequiredArgsConstructor
public class OptionsController {

    private final OptionsService optionsService;

    @PostMapping("${create.option}")
    public ResponseEntity<Options> save(
            @RequestBody OptionsDto options
    ) {
        return ResponseEntity.ok(optionsService.save(options));
    }

    @GetMapping("${all.options}")
    public ResponseEntity<List<OptionsDto>>findAll() { return ResponseEntity.ok(optionsService.findAll());}

    @GetMapping("${option.by.type}")
    public ResponseEntity<OptionsDto>findOptionByType (
            @PathVariable("option-type")String optionType
    ) {
        return ResponseEntity.ok(optionsService.findByName(optionType));
    }

    @DeleteMapping("${delete.option.id}")
    public ResponseEntity<Void> delete(
            @PathVariable("option-id") Integer id
    ) {
        optionsService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
