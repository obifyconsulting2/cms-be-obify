package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.ErrorDTO;
import com.obifyconsulting.cmsbe.dto.LectureDTO;
import com.obifyconsulting.cmsbe.dto.SectionDTO;
import com.obifyconsulting.cmsbe.entity.Course;
import com.obifyconsulting.cmsbe.entity.Lecture;
import com.obifyconsulting.cmsbe.entity.Section;
import com.obifyconsulting.cmsbe.exception.BusinessException;
import com.obifyconsulting.cmsbe.repository.LectureRepository;
import com.obifyconsulting.cmsbe.repository.SectionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private SectionRepository sectionRepository;

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public Long createLecture(LectureDTO lectureDTO) {

        Optional<Lecture> optTask = lectureRepository.findByTitleContains(lectureDTO.getTitle());
        if(optTask.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode("DUP_001");
            errorDTO.setMessage(String.format("Lecture with title %s already exist", lectureDTO.getTitle()));
            List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
            throw new BusinessException(errorDTOS);
        }
        Lecture lecture = new Lecture();
        BeanUtils.copyProperties(lectureDTO, lecture);
        Section section = sectionRepository.findById(lectureDTO.getSectionId()).get();
        lecture.setSection(section);
        lecture = lectureRepository.save(lecture);
        return lecture.getId();
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public LectureDTO getLecture(Long lectureId) {
        Optional<Lecture> optionalLecture = lectureRepository.findById(lectureId);
        LectureDTO lectureDTO = new LectureDTO();
        BeanUtils.copyProperties(optionalLecture.get(), lectureDTO);
        return lectureDTO;
    }

    @Override
    public List<LectureDTO> getAllLecturesBySectionId(Long sectionId) {
        List<Lecture> lectures = lectureRepository.findAllBySectionId(sectionId);
        List<LectureDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        lectures.forEach((lecture)->{
            LectureDTO lectureDTO = new LectureDTO();
            BeanUtils.copyProperties(lecture, lectureDTO);
            lectureDTO.setUrl(null);
            lectureDTO.setPublished(null);
            lectureDTO.setSectionType(null);
            dtoList.add(lectureDTO);
        });
        return dtoList;
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public List<LectureDTO> getAllLecturesBySectionIdWithDetails(Long sectionId) {
        List<Lecture> lectures = lectureRepository.findAllBySectionId(sectionId);
        List<LectureDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        lectures.forEach((lecture)->{
            LectureDTO lectureDTO = new LectureDTO();
            BeanUtils.copyProperties(lecture, lectureDTO);
            dtoList.add(lectureDTO);
        });
        return dtoList;
    }
}
