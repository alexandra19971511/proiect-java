package com.proiect.model;


//Table users as U {
//        id int [pk, increment] // auto-increment
//        email_address varchar
//        password varchar
//        full_name varchar
//        created_at timestamp
//        role varchar
//        }

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static com.proiect.model.User.TABLE_NAME;

@Entity
@Getter
@Table(name = TABLE_NAME)
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
    protected static final String TABLE_NAME = "app_user";
    private static final String USER_SEQ = "USER_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = USER_SEQ)
    @SequenceGenerator(sequenceName = "user_seq", initialValue = 1, allocationSize = 1, name = USER_SEQ)
    private Long id;

    private String email;
    private String password;
    private String fullName;
    private Date createdAt;
    private String role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    private User() {
        this.role = Role.ROLE_USER.name();
        this.createdAt = new Date();
    }

    public User(String email, String password, String fullName) {
        this();
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public User(Long id, String email, String password, String fullName) {
        this();
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public User(Long id) {
        this();
        this.id = id;
    }

}
