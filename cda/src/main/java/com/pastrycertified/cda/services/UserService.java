package com.pastrycertified.cda.services;

//import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.models.User;

import java.util.Map;

public interface UserService extends AbstractService<UserDto> {


    //User updateOneInfo(Integer id, Map<Object, Object> objectMap);


//    Integer save(Map<String, Object> user,Integer userId);
    ///Object save(Integer userId, Map<UserDto>user);

//    Integer save(Integer id );

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
