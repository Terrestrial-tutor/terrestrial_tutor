package com.example.terrestrial_tutor.entity;

import javax.persistence.*;

import com.example.terrestrial_tutor.entity.enums.ERole;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tutors", schema = "public")
public class TutorEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_sequence")
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 10)
    private Long id;

    @Column(name = "subjects")
    @ManyToMany(mappedBy = "tutors", fetch = FetchType.LAZY)
    List<SubjectEntity> subjects = new ArrayList<>();

    @ManyToMany()
    @JoinColumn(name = "pupils")
    List<PupilEntity> pupils = new ArrayList<>();

    @Column(name = "payment_data")
    String paymentData;

    @OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY)
    List<HomeworkEntity> homeworkList = new ArrayList<>();

    @NonNull
    @Column(name = "username")
    String username;

    @NonNull
    @Column(name = "name")
    String name;

    @NonNull
    @Column(name = "surname")
    String surname;

    @Column(name = "patronymic")
    String patronymic;

    @NonNull
    @Column(name = "email")
    String email;

    @NonNull
    @Column(name = "password")
    String password;

    @NonNull
    @Column(name = "role")
    ERole role;

    @NonNull
    @Column(name = "verification")
    Boolean verification = false;

    @Transient
    private GrantedAuthority authorities;

    public TutorEntity(Long id,
                String username,
                String name,
                String surname,
                String patronymic,
                String email,
                String password,
                ERole role,
                Boolean verification,
                GrantedAuthority authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.role = role;
        this.verification = verification;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
