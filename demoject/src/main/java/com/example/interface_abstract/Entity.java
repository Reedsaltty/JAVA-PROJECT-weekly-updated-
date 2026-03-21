package com.example.interface_abstract;

public abstract class Entity {
    protected String id;
    
    public Entity(String id) {
        setId(id);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String displayInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInfo'");
    }
}
