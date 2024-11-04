package com.comparus.converter;

import com.comparus.domain.User;
import com.comparus.dto.response.UserReadResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserReadResponseConverter implements Converter<User, UserReadResponse> {

    @Override
    public UserReadResponse convert(User source) {
        return UserReadResponse.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .surname(source.getSurname())
                .username(source.getUsername())
                .build();
    }
}
