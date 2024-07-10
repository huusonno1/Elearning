package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Module")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_module", length = 350)
    private String nameModule;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
