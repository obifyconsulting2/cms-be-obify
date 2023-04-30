package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.SectionDTO;

import java.util.List;

public interface SectionService {
    Long createSection(SectionDTO sectionDTO);
    SectionDTO getSection(Long sectionId);
    List<SectionDTO> getAllSectionsByCourseId(Long courseId);
}
