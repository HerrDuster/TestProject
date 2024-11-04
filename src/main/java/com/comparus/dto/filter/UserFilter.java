package com.comparus.dto.filter;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFilter {

    @Size(min = 2, max = 30, message = "Username має бути від 3 до 30 символів")
    @Pattern(regexp = "^[a-zA-Z0-9*?_.-]*$", message = "Username містить недозволені символи")
    private String username;

    @Size(min = 2, max = 30, message = "firstName має бути від 3 до 30 символів")
    @Pattern(regexp = "^[a-zA-Z*?_.-]*$", message = "First name містить недозволені символи")
    private String firstName;

    @Size(min = 2, max = 30, message = "surname має бути від 3 до 30 символів")
    @Pattern(regexp = "^[a-zA-Z*?_.-]*$", message = "surname містить недозволені символи")
    private String surname;

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameter(in = ParameterIn.QUERY,
            description = "Username as filter. Can be used with: '*', '_', '[A-S]', '[^A-B]'",
            name = "username",
            schema = @Schema(type = "string"))
    @Parameter(in = ParameterIn.QUERY,
            description = "First name as filter. Can be used with: '*', '_', '[A-S]', '[^A-B]'",
            name = "firstName",
            schema = @Schema(type = "string"))
    @Parameter(in = ParameterIn.QUERY,
            description = "surname as filter. Can be used with: '*', '_', '[A-S]', '[^A-B]'",
            name = "surname",
            schema = @Schema(type = "string"))
    public @interface QueryParameters {
    }
}
