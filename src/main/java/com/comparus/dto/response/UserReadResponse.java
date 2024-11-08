package com.comparus.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserReadResponse {
    private UUID id;
    private String username;
    private String firstName;
    private String surname;
}
