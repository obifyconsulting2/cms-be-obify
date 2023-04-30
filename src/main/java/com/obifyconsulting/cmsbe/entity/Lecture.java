package com.obifyconsulting.cmsbe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lectures")
@Data
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String lectureType;
    @Column
    private String url;
    @Column
    private String description;
    @Column
    private Boolean published;
    @ManyToOne(cascade = CascadeType.ALL)
    private Section section;
}
