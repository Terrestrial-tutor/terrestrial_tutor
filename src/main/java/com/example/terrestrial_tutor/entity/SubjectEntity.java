package com.example.terrestrial_tutor.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "subjects", schema = "public")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;

    @NonNull
    @Column(name = "count_level")
    int countLevel;

    @ManyToMany()
    @JoinColumn(name = "tutors")
    List<TutorEntity> tutors;

    @ManyToMany()
    @JoinColumn(name = "pupils")
    List<PupilEntity> pupils;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<TaskEntity> tasks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<HomeworkEntity> homeworkList;
}
