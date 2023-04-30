package com.obifyconsulting.cmsbe.repository;

import com.obifyconsulting.cmsbe.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByTitleContains(String title);
    List<Lecture> findAllBySectionId(Long sectionId);
}
