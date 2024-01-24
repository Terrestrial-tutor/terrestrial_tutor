package com.example.terrestrial_tutor.entity;

import com.example.terrestrial_tutor.dto.UserRegistration;
import com.example.terrestrial_tutor.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private int id;

    @NonNull
    @Column(name = "username")
    String username;

    @NonNull
    @Column(name = "password")
    String password;

    @NonNull
    @Column(name = "role")
    String role;

    @Column(name = "system_id")
    int systemId;

    public User(UserRegistration user) {
        username = user.getUsername();
        password = user.getPassword();
        role = user.getRole();
    }
}
