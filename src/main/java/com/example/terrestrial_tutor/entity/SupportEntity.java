package com.example.terrestrial_tutor.entity;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "supports_additional_info", schema = "public")
public class SupportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @NonNull
    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "support", fetch = FetchType.LAZY)
    List<PupilEntity> pupils;

    @NonNull
    @Column(name = "telegram_tag")
    String telegramTag;

    @Column(name = "payment_data")
    String paymentData;
}
