package com.example.interface_abstract;

public abstract class Entity {
    protected String id;
    
    public Entity(String Id) {
        setId(Id);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) throws com.example.exceptions.ValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new com.example.exceptions.ValidationException("ID cannot be null or empty");
        }
        this.id = id;
    }
}
