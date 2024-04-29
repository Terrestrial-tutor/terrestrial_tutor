package com.example.terrestrial_tutor.entity;

import com.example.terrestrial_tutor.entity.enums.TaskCheckingType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "completed_task", schema = "public")
public class CompletedTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "task_checking_type")
    TaskCheckingType taskCheckingType;

    @ManyToOne()
    @JoinColumn(name = "task")
    TaskEntity task;

    @ManyToOne()
    @JoinColumn(name = "homework")
    HomeworkEntity homework;

    @Column(name = "position")
    Integer index;

    public CompletedTaskEntity(TaskEntity taskEntity, TaskCheckingType check){
        this.task = taskEntity;
        this.homework = homework;
        this.taskCheckingType = check;
    }
}
