package com.example.terrestrial_tutor.entity;

import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "subject")
    SubjectEntity subject;

    //tasks

    //@Column(name = "banList")
    //List<Integer> = new ArrayList<Integer>();

    @Column(name = "solute_time")
    Long soluteTime;

    Long targetTime;

    @ManyToMany()
    @JoinColumn(name = "pupils")
    List<PupilEntity> pupils;

//    todo
//    Добавить теоритические материалы

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor")
    TutorEntity tutor;

    @ElementCollection
    @CollectionTable(name = "checking_map")
    @MapKeyColumn(name = "key_column")
    Map<TaskEntity, TaskCheckingType> tasksCheckingTypes;
    LocalDate deadLine;
}
