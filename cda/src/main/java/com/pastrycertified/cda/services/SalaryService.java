package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.SalaryDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;


public interface SalaryService extends AbstractService<SalaryDto>{

    AuthenticationResponse register(SalaryDto admin);

    AuthenticationResponse registerPastryChef(SalaryDto admin);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    SalaryDto findAdminById(Integer id);

    Integer savePastryChef(SalaryDto dto);
}
