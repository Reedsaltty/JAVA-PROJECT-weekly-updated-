package com.example.services;

import com.example.interface_abstract.Entity;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseService<T extends Entity> {
    protected List<T> entities = new ArrayList<>();

    public T findById(String id) {
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public void add(T entity) {
        entities.add(entity);
    }

    public List<T> getAll() {
        return new ArrayList<>(entities);
    }

    public void remove(String id) {
        entities.removeIf(e -> e.getId().equals(id));
    }
}
