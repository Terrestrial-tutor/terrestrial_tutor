package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

/** Сущность ответа ученика на задание
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "answers", schema = "public")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "answer")
    String answer;

    @ManyToOne()
    @JoinColumn(name = "homework")
    HomeworkEntity homework;

    @ManyToOne
    @JoinColumn(name ="task")
    TaskEntity task;

    @ManyToOne()
    @JoinColumn(name = "pupil")
    PupilEntity pupil;
}
