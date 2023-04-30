package com.obifyconsulting.cmsbe.repository;

import com.obifyconsulting.cmsbe.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByTitleContains(String title);
    List<Course> findAllByTitleContains(String title);
    List<Course> findAllByInstructorId(Long id);
}
