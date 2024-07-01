package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_lesson")
    private String nameLesson;

    @Column(name = "video_url", length = 500)
    private String videoUrl;

    @Column(name = "document", length = 500)
    private String document;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "thumbnail", length = 500)
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

}
