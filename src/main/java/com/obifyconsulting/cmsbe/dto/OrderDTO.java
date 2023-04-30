package com.obifyconsulting.cmsbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private boolean paid;
    private boolean enrolled;
    private LocalDateTime enrolledDateTime;
}
