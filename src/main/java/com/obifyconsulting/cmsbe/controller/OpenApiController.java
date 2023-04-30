package com.obifyconsulting.cmsbe.controller;

import com.obifyconsulting.cmsbe.dto.CourseDTO;
import com.obifyconsulting.cmsbe.dto.LectureDTO;
import com.obifyconsulting.cmsbe.dto.SectionDTO;
import com.obifyconsulting.cmsbe.service.CourseService;
import com.obifyconsulting.cmsbe.service.LectureService;
import com.obifyconsulting.cmsbe.service.SectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openapi/v1")
@Log4j2
public class OpenApiController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private LectureService lectureService;

    @GetMapping("/lectures/{sectionId}")
    public ResponseEntity<List<LectureDTO>> getAllLecturesBySectionId(@PathVariable Long sectionId){
        List<LectureDTO> lectures = lectureService.getAllLecturesBySectionId(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(lectures);
    }

    @GetMapping("/sections/{courseId}")
    public ResponseEntity<List<SectionDTO>> getAllSectionsByCourseId(@PathVariable Long courseId){
        List<SectionDTO> sections = sectionService.getAllSectionsByCourseId(courseId);
        return ResponseEntity.status(HttpStatus.OK).body(sections);
    }

    @GetMapping("/search-courses")
    public ResponseEntity<List<CourseDTO>> searchCourse(@RequestParam("q") String courseTitle){
        List<CourseDTO> courseDTOS = courseService.searchCourseByTitle(courseTitle);
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOS);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        List<CourseDTO> courseDTOS = courseService.getAllCourses();
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOS);
    }
}
