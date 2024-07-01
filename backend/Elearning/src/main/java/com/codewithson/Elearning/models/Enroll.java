package com.codewithson.Elearning.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enrolls")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enroll extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "email", length = 100)
    private String email;

    @NotNull
    @Column(name = "phone_number", length = 100)
    private String phoneNumber;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "enroll_date")
    private LocalDateTime enrollDate;

    @Column(name = "total_money")
    private Float totalMoney;

    @OneToMany(mappedBy = "enroll", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnrollDetail> enrollDetails;
}
