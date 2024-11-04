package com.comparus.service.impl;

import com.comparus.domain.User;
import com.comparus.dto.filter.UserFilter;
import com.comparus.repository.UserRepository;
import com.comparus.repository.UserSpecificationFactory;
import com.comparus.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String PATTERN_CHAR_FROM_API = "\\*";
    private static final String PATTERN_CHAR_TO_DB = "%";

    private final UserRepository userRepository;
    private final UserSpecificationFactory userSpecificationFactory;

    @Override
    public List<User> findAll(UserFilter userFilter) {
        Specification<User> specification = buildUserSpecification(userFilter);
        return userRepository.findAll(specification);
    }

    private Specification<User> buildUserSpecification(UserFilter userFilter) {
        Specification<User> specification = userSpecificationFactory.conjunction();
        if (Objects.nonNull(userFilter.getUsername()) && !userFilter.getUsername().isEmpty()) {
            specification = specification.and(userSpecificationFactory
                    .likeUsername(userFilter
                            .getUsername()
                            .replaceAll(PATTERN_CHAR_FROM_API, PATTERN_CHAR_TO_DB)));
        }
        if (Objects.nonNull(userFilter.getFirstName()) && !userFilter.getFirstName().isEmpty()){

            specification = specification.and(userSpecificationFactory
                    .likeFirstName(userFilter
                            .getFirstName()
                            .replaceAll(PATTERN_CHAR_FROM_API, PATTERN_CHAR_TO_DB)));
        }
        if (Objects.nonNull(userFilter.getSurname()) && !userFilter.getSurname().isEmpty()){
            specification = specification.and(userSpecificationFactory
                    .likeSurname(userFilter
                            .getSurname()
                            .replaceAll(PATTERN_CHAR_FROM_API, PATTERN_CHAR_TO_DB)));
        }
        return specification;
    }
}
