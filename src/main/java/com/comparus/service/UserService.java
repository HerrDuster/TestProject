package com.comparus.service;

import com.comparus.domain.User;
import com.comparus.dto.filter.UserFilter;

import java.util.List;

public interface UserService {

    List<User> findAll(UserFilter userFilter);
}
