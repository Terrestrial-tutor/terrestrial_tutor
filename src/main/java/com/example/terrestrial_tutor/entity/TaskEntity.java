package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @NonNull
    @Column(name = "checking")
    int checking;

    @NonNull
    @Column(name = "answer_type")
    String answerType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "task_files", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "files")
    Set<String> files;
    /*
    @NonNull
    @Column(name = "complexity")
    String complexity;
    */
    @Column(name = "task_text", columnDefinition="text")
    String taskText;

    //task_images

    //task_tables_ids

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "answers_array", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "answers")
    List<String> answer;
    //todo проверить инициализацию
    @ManyToOne()
    @JoinColumn(name = "subject")
    SubjectEntity subject;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<AnswerEntity> answerEntities;

    @NonNull
    @Column(name = "level1")
    String level1;

    @Column(name = "level2")
    String level2;

   // @Column(name = "link_to_solution")
   // String linkToSolution;

    @NonNull
    @Column(name = "tables")
    String table;

    @ManyToOne()
    @JoinColumn(name = "support")
    SupportEntity support;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<CompletedTaskEntity> completedTaskEntities;

    //ban_commands

    //task_analysis
}
