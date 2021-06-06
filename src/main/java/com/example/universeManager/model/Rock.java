package com.example.universeManager.model;

import com.example.universeManager.entity.RockEntity;

public class Rock {

    private Long id;
    private String name;

    public static Rock toModel (RockEntity entity) {
        Rock model = new Rock();
        model.setId(entity.getId());
        model.setName(entity.getName());
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
}
