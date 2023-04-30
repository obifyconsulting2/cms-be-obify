package com.obifyconsulting.cmsbe.repository;

import com.obifyconsulting.cmsbe.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByTitleContains(String title);
    List<Section> findAllByCourseId(Long courseId);
}
