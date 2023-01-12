package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.AdminDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;


public interface AdminService extends AbstractService<AdminDto>{

    AuthenticationResponse register(AdminDto admin);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AdminDto findAdminById(Integer id);

}
