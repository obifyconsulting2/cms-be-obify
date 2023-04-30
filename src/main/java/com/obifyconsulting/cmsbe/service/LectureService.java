package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.LectureDTO;

import java.util.List;

public interface LectureService {
    Long createLecture(LectureDTO lectureDTO);
    LectureDTO getLecture(Long lectureId);
    List<LectureDTO> getAllLecturesBySectionId(Long sectionId);
    List<LectureDTO> getAllLecturesBySectionIdWithDetails(Long sectionId);
}
