package com.example.universeManager.model;

import com.example.universeManager.entity.MasterEntity;

public class Master {

    private Long id;
    private String name;
    private int age;

    public static Master toModel(MasterEntity entity) {
        Master model = new Master();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setAge(entity.getAge());
        return model;
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
