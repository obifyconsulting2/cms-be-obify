package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.ErrorDTO;
import com.obifyconsulting.cmsbe.dto.SectionDTO;
import com.obifyconsulting.cmsbe.entity.Course;
import com.obifyconsulting.cmsbe.entity.Section;
import com.obifyconsulting.cmsbe.exception.BusinessException;
import com.obifyconsulting.cmsbe.repository.CourseRepository;
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
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public Long createSection(SectionDTO sectionDTO) {

        Optional<Section> optTask = sectionRepository.findByTitleContains(sectionDTO.getTitle());
        if(optTask.isPresent()){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setCode("DUP_001");
            errorDTO.setMessage(String.format("Section with title %s already exist", sectionDTO.getTitle()));
            List<ErrorDTO> errorDTOS = Arrays.asList(errorDTO);
            throw new BusinessException(errorDTOS);
        }

        Course course = courseRepository.findById(sectionDTO.getCourseId()).get();
        Section section = new Section();
        BeanUtils.copyProperties(sectionDTO, section);
        section.setCourse(course);
        section = sectionRepository.save(section);
        return section.getId();
    }

    @Override
    public SectionDTO getSection(Long sectionId) {
        Optional<Section> optionalSection = sectionRepository.findById(sectionId);
        SectionDTO sectionDTO = new SectionDTO();
        BeanUtils.copyProperties(optionalSection.get(), sectionDTO);
        return sectionDTO;
    }

    @Override
    public List<SectionDTO> getAllSectionsByCourseId(Long courseId) {
        List<Section> sections = sectionRepository.findAllByCourseId(courseId);
        List<SectionDTO> dtoList = new ArrayList<>();
        /**One by one convert every Entity to DTO and add to DTO list and finally return the DTO list**/
        sections.forEach((course)->{
            SectionDTO sectionDTO = new SectionDTO();
            BeanUtils.copyProperties(course, sectionDTO);
            dtoList.add(sectionDTO);
        });
        return dtoList;
    }

}
