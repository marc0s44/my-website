package com.mywebsite.users;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class websiteUser {

    @Id
    @Type(type = "pg-uuid")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String password;

    @Column(unique = true)
    private String email;
}
