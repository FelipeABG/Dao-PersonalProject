package dao;

import entities.Entity;
import java.util.List;

public interface EntitiesDao<entity extends Entity> {

    default void insert(entity o){

    }
    default void update(entity o){

    }
    default void deleteById(Integer id){

    }
    entity findById(Integer id);
    List<entity> findAll();
}
