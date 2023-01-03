package com.jjj.cashlesstips.controller.external;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jjj.cashlesstips.dto.UserRequest;
import com.jjj.cashlesstips.dto.UserResponse;
import com.jjj.cashlesstips.mapper.UserMapper;
import com.jjj.cashlesstips.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController

@RequiredArgsConstructor
public class UserControllerPublicV1 {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userMapper.mapEntityToUserResponse(
            userService.createUser(userMapper.mapUserRequestToEntity(request))
        );
    }
}
