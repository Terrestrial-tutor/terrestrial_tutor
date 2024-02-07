package com.example.terrestrial_tutor.entity;

import com.example.terrestrial_tutor.entity.enums.ERole;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "checks", schema = "public")
public class CheckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "candidate_role")
    ERole role;

    @Column(name = "date")
    Date date;

    @Column(name = "candidate_id")
    Long candidateId;
}
