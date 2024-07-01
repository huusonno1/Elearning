package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Module")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
