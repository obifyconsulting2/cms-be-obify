package com.obifyconsulting.cmsbe.controller;

import com.obifyconsulting.cmsbe.dto.SectionDTO;
import com.obifyconsulting.cmsbe.service.SectionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Log4j2
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @PostMapping("/sections")
    public ResponseEntity<String> createSection(@RequestBody SectionDTO sectionDTO){
        Long sectionId = sectionService.createSection(sectionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Section created successfully with Id "+sectionId);
    }
}
