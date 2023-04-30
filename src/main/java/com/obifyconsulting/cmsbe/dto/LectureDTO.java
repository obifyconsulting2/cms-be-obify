package com.obifyconsulting.cmsbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureDTO {
    private Long id;
    private String title;
    private String sectionType;
    private String url;
    private String description;
    private Boolean published;
    private Long sectionId;
}
