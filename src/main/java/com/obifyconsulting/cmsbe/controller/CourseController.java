package com.obifyconsulting.cmsbe.controller;

import com.obifyconsulting.cmsbe.dto.CourseDTO;
import com.obifyconsulting.cmsbe.service.CourseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses-by-instructor")
    public ResponseEntity<List<CourseDTO>> getAllCourseByInstructor(){
        List<CourseDTO> courseDTOS = courseService.getAllCoursesByInstructor();
        return ResponseEntity.status(HttpStatus.OK).body(courseDTOS);
    }

    @PostMapping("/courses")
    public ResponseEntity<String> createCourse(@RequestBody CourseDTO courseDTO){
       Long courseId = courseService.createCourse(courseDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully with Id "+courseId);
    }
}
