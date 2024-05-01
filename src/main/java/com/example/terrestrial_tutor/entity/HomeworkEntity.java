package com.example.terrestrial_tutor.entity;

import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @Column(name = "name")
    String name;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject")
    SubjectEntity subject;

    //tasks

    //@Column(name = "banList")
    //List<Integer> = new ArrayList<Integer>();

    @Column(name = "solute_time")
    Long soluteTime;

    Long targetTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pupils")
    Set<PupilEntity> pupils;

//    todo
//    Добавить теоритические материалы

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tutor")
    TutorEntity tutor;

    @OneToMany(mappedBy = "homework", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn(name = "position")
    List<CompletedTaskEntity> completedTaskEntities = new LinkedList<>();

    @OneToMany(mappedBy = "homework", fetch = FetchType.EAGER)
    List<AnswerEntity> answerEntities;

    /*
    @ElementCollection
    @CollectionTable(name = "checking_map")
    @MapKeyColumn(name = "key_column")
    Map<TaskEntity, TaskCheckingType> tasksCheckingTypes;

     */
    LocalDate deadLine;
}
