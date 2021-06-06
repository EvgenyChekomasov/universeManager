package com.example.universeManager.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class MasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private boolean haveRock = false;

    @OneToMany (mappedBy = "master")
    private List <RockEntity> rocks;

    public boolean isHaveRock() {
        return haveRock;
    }

    public void setHaveRock(boolean haveRock) {
        this.haveRock = haveRock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
