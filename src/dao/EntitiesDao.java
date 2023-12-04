package dao;

import java.util.List;

public interface EntitiesDao<entity> {

    void insert(entity o);
    void update(entity o);
    void deleteById(Integer id);
    entity findById(Integer id);
    List<entity> findAll();
}
