package com.example.terrestrial_tutor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.mapping.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "pupils", schema = "public")
public class PupilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pupils_id_seq")
    @SequenceGenerator(name = "pupils_id_seq", sequenceName = "pupils_id_seq", allocationSize = 1)
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;

    String subjects;

    Integer price;

    int[] tutor_id;

    Integer supervisor_id;

    int[] hw_list;

    Double balance;

}
