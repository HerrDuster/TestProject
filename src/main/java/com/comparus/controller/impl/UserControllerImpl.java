package com.comparus.controller.impl;

import com.comparus.controller.UserController;
import com.comparus.dto.filter.UserFilter;
import com.comparus.dto.response.UserReadResponse;
import com.comparus.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    @Override
    public List<UserReadResponse> findAll(@Valid UserFilter userFilter) {
        return userService.findAll(userFilter)
                .stream()
                .map(user -> conversionService.convert(user, UserReadResponse.class))
                .toList();
    }
}
