package com.obifyconsulting.cmsbe.service;

import com.obifyconsulting.cmsbe.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    Long createCourse(CourseDTO courseDTO);
    CourseDTO getCourse(Long courseId);
    List<CourseDTO> getAllCourses();
    List<CourseDTO> getAllCoursesByInstructor();
    CourseDTO deleteCourse(Long courseId);
    CourseDTO updateCourse(CourseDTO courseDTO);
    CourseDTO updateCoursePrice(CourseDTO courseDTO);
    List<CourseDTO> searchCourseByTitle(String title);

}
