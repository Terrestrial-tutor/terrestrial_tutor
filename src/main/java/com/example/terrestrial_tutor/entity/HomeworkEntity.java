package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "homeworks", schema = "public")
public class HomeworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;

    //tasks

    //@Column(name = "banList")
    //List<Integer> = new ArrayList<Integer>();

    @Column(name = "solute_time")
    Time soluteTime;

    @ManyToOne()
    @JoinColumn(name = "pupil")
    PupilEntity pupil;

    @ManyToOne()
    @JoinColumn(name = "tutor")
    TutorEntity tutor;
}
