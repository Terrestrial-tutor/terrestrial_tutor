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
@Table(name = "homework_solutions", schema = "public")
public class AttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "answers", columnDefinition="text")
    String answers;

    @ManyToOne()
    @JoinColumn(name = "homework")
    HomeworkEntity homework;

    @ManyToOne()
    @JoinColumn(name = "pupil")
    PupilEntity pupil;

    @Column(name = "attempt_number")
    Integer attemptNumber = 0;
}
