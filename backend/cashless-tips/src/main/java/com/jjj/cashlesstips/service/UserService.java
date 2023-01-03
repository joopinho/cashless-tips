package com.jjj.cashlesstips.service;

import java.util.Optional;

import com.jjj.cashlesstips.dao.entity.User;

public interface UserService {

    public User createUser(User user);

    public User getUserById(Long id);

    public Optional<Long> getCurrentUserId();
}
