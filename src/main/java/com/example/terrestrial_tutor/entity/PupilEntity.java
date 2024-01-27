package com.example.terrestrial_tutor.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.List;
import org.springframework.lang.Nullable;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "pupils")
public class PupilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pupils_id_seq")
    @SequenceGenerator(name = "pupils_id_seq", sequenceName = "pupils_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    String name;

    @Nullable
    String subjects;

    Integer price;

    Integer tutor_id;

    Integer[] hw_list;

    Double balance;

}
