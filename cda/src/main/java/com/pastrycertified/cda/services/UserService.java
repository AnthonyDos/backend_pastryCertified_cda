package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    UserDto findUserById(Integer id);
}
