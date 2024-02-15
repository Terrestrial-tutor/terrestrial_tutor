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

    @ManyToOne()
    @JoinColumn(name = "subject")
    SubjectEntity subject;

    @NonNull
    @Column(name = "level1")
    String level1;

    @NonNull
    @Column(name = "level2")
    String level2;

    @ManyToMany()
    @JoinColumn(name = "homeworks")
    List<HomeworkEntity> homeworks = new ArrayList<>();

    //ban_commands

    //task_analysis
}
