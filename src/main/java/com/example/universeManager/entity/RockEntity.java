package com.example.universeManager.entity;

import javax.persistence.*;

@Entity
public class RockEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean haveMaster = false;

    public MasterEntity getMaster() {
        return master;
    }

    public void setMaster(MasterEntity master) {
        this.master = master;
    }

    @ManyToOne
    @JoinColumn (name = "master_id")
    private MasterEntity master;

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

    public boolean isHaveMaster() {
        return haveMaster;
    }

    public void setHaveMaster(boolean haveMaster) {
        this.haveMaster = haveMaster;
    }
}
