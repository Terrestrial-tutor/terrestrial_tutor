package com.example.terrestrial_tutor.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tutors_additional_info", schema = "public")
public class TutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "subjects")
    @ManyToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    List<SubjectEntity> subjects = new ArrayList<>();

    @ManyToMany()
    @JoinColumn(name = "pupils")
    List<PupilEntity> pupils = new ArrayList<>();

    @Column(name = "payment_data")
    String paymentData;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    List<HomeworkEntity> homeworkList = new ArrayList<>();
}
