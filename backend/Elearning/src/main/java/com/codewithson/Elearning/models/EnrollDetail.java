package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enroll_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollDetail extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enroll_id")
    private Enroll enroll;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "price", nullable = false)
    private Float price;

}
