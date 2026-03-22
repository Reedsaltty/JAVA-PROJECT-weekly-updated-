package com.example.interface_abstract;

public abstract class Entity {
    protected String id;
    
    public Entity(String seatId) {
        setId(seatId);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    
}
