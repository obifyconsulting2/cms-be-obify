package com.obifyconsulting.cmsbe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private Boolean published;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;
}
