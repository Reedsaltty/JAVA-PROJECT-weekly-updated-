package com.example.interface_abstract;
import com.example.exceptions.ValidationException;  
public abstract class Entity {
    protected String id;
    
    public Entity(String Id) {
        setId(Id);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) throws ValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new ValidationException("ID cannot be null or empty");
        }
        this.id = id;
    }
}
