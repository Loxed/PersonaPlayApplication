package com.example.personaplayfront.DAO;

import java.util.List;
public abstract class Dao<T> {

    protected final Class<T> type;

    public Dao(Class<T> type) {
        this.type = type;
    }

    //create new entity
    public abstract void save(T t);

    //find entity by id
    public abstract T findById(int id);

    //update entity
    public abstract void update(T t);

    //delete entity
    public abstract boolean deleteById(int id);

    //find all entities
    public abstract List<T> findAll();

    //find by property (exact match)
    public abstract List<T> findByProperty(String propertyName, Object propertyValue);

    //get entity type
    public Class<T> getType() {
        return type;
    }

}
