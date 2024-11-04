package com.comparus.repository;

import com.comparus.domain.User;
import com.comparus.domain.User_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserSpecificationFactory {
    private final Map<String, Specification<User>> cachedSpecifications = new HashMap<>();

    public Specification<User> conjunction() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.conjunction();
    }

    public Specification<User> likeUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(User_.USERNAME), username);
    }

    public Specification<User> likeFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(User_.FIRST_NAME), firstName);
    }

    public Specification<User> likeSurname(String surname) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(User_.SURNAME), surname);
    }

    private Specification<User> createLikeSpecification(String attributeName, String value) {
        String cacheKey = attributeName + ":" + value;
        return cachedSpecifications.computeIfAbsent(cacheKey, key -> (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(attributeName), value));
    }
}
