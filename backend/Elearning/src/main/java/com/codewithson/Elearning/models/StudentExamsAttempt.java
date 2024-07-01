package com.codewithson.Elearning.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "student_exams_attempt")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentExamsAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attempt_date")
    private Date attemptDate;

    @Column(name = "marks")
    private Integer marks;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
