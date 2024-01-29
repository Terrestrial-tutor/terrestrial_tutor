package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "payments", schema = "public")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "pupil")
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    PupilEntity pupil = new PupilEntity();

    @Column(name = "tutor")
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    TutorEntity tutor = new TutorEntity();

    @Column(name = "lesson_time")
    Time lessonTime;

    //int hw_id;

    @Column(name = "lesson_date")
    Date lessonDate;

    @Column(name = "status")
    String status;
}
