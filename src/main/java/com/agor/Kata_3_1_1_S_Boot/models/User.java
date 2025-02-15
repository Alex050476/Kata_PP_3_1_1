package com.agor.Kata_3_1_1_S_Boot.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotEmpty(message = "Fill the field")
    @Size(min = 2, max = 50, message = "Name should be between 2 & 50 chars")
    private String name;

    @Column(name = "usernickname")
    @NotEmpty(message = "Fill the field")
    @Size(min = 2, max = 50, message = "Name should be between 2 & 50 chars")
    private String nickname;

    public User() {}

    public User(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getNickname();
    }
}
