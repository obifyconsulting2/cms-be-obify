package com.obifyconsulting.cmsbe.controller;

import com.obifyconsulting.cmsbe.dto.LectureDTO;
import com.obifyconsulting.cmsbe.service.LectureService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class LectureController {

    @Autowired
    private LectureService lectureService;

    @GetMapping("/open/lectures/{sectionId}")
    public ResponseEntity<List<LectureDTO>> getAllLecturesBySectionId(@PathVariable Long sectionId){
        List<LectureDTO> lectures = lectureService.getAllLecturesBySectionId(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(lectures);
    }

    @GetMapping("/lectures/{sectionId}")
    public ResponseEntity<List<LectureDTO>> getAllLecturesBySectionIdWithDetails(@PathVariable Long sectionId){
        List<LectureDTO> lectures = lectureService.getAllLecturesBySectionId(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(lectures);
    }

    @PostMapping("/lectures")
    public ResponseEntity<String> createLecture(@RequestBody LectureDTO lectureDTO){
        Long lectureId = lectureService.createLecture(lectureDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lecture created successfully with Id "+lectureId);
    }
}
