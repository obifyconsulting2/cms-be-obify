package com.obifyconsulting.cmsbe.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String subTitle;
    @Column
    private String description;
    @Column
    @Lob
    private String image;
    @Column
    private Double price;
    @Column
    private boolean active;
    @Column
    private boolean published;

    @ManyToOne(cascade = CascadeType.ALL)
    private User instructor;
    private LocalDateTime creationDateTime;
}
