package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "options")
    private String options;

    @Column(name = "correct_ans")
    private String correctAns;

    @Column(name = "marks")
    private Integer marks;

    @ManyToOne
    @JoinColumn(name = "exams_id")
    private Exams exams;

}
