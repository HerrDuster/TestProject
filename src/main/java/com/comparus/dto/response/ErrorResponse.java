package com.comparus.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private int status;

    @Singular
    private List<String> errors;

    private LocalDateTime timestamp;

    private String message;

    private String path;
}
