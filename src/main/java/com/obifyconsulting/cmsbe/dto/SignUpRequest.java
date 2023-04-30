package com.obifyconsulting.cmsbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
}
