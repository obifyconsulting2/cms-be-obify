package com.obifyconsulting.cmsbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDTO {

    private Long id;
    private String title;
    private String subTitle;
    private String description;
    private String image;
    private Double price;
    private Boolean active;
    private Boolean published;

}
