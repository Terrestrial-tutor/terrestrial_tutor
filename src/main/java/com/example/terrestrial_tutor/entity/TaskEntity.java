package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tasks", schema = "public")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;
    /*
    @NonNull
    @Column(name = "checking")
    int checking;

    @NonNull
    @Column(name = "answer_type")
    String answerType;

    @Column(name = "files")
    String files;

    @NonNull
    @Column(name = "complexity")
    String complexity;

    @Column(name = "task_text")
    String taskText;

     */

    //task_images

    //task_tables_ids

    @NonNull
    @Column(name = "answer")
    String answer;

    //ban_commands

    //task_analysis
}
