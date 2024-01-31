package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "pupils_additional_info", schema = "public")
public class PupilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @NonNull
    @Column(name = "balance")
    Double balance;

    @OneToMany(mappedBy = "pupil", fetch = FetchType.LAZY)
    List<HomeworkEntity> homeworkList = new ArrayList<>();
    @Column(name = "price")
    int price;

    @ManyToMany(mappedBy = "pupils", fetch = FetchType.LAZY)
    List<SubjectEntity> subjects = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "support")
    SupportEntity support;

    @ManyToMany(mappedBy = "pupils", fetch = FetchType.LAZY)
    List<TutorEntity> tutors = new ArrayList<>();

    @OneToMany(mappedBy = "pupil", fetch = FetchType.LAZY)
    List<PaymentEntity> payments= new ArrayList<>();
}
