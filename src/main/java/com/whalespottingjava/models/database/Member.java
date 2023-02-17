package com.whalespottingjava.models.database;

import com.whalespottingjava.models.enums.MemberRole;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    private static final String ID_SEQUENCE = "member_id_sequence";

    @Id
    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    protected Member() {}

    public Member(String username, String password, MemberRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = MemberRole.USER;
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
}