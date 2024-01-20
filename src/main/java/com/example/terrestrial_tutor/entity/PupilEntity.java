package com.example.terrestrial_tutor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
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
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;

    List subjects;

    Integer price;

    List tutor_id;

    Integer supervisor_id;

    List hw_list;

    Double balance;

}
