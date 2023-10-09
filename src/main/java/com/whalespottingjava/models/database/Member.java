package com.whalespottingjava.models.database;

import com.whalespottingjava.models.enums.MemberRole;
import jakarta.persistence.*;

@Entity
public class Member {
    private static final String ID_SEQUENCE = "member_id_sequence";

    @Id
    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String surname;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    protected Member() {}

    public Member(String username, String password, MemberRole role, String email, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    public Member(String username, String password, String email, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.role = MemberRole.USER;
        this.email = email;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}