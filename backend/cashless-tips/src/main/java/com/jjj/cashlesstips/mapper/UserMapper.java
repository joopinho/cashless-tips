package com.jjj.cashlesstips.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jjj.cashlesstips.dao.entity.User;
import com.jjj.cashlesstips.dto.UserRequest;
import com.jjj.cashlesstips.dto.UserResponse;

@Mapper (componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface UserMapper {
    
    @Mapping(source = "password", target = "password", qualifiedByName ="encodedMapping")
    User mapUserRequestToEntity(UserRequest request);

    UserResponse mapEntityToUserResponse(User user);
    
}
