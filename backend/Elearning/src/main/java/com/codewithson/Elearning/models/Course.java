package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_course", length = 300)
    private String nameCourse;

    @Column(name = "description", length = 500)
    private String description;

    private Float price;

    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "course",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Module> modules ;

    // for user_admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User admin;

}
