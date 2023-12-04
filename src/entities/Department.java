package entities;

import dao.EntitiesDao;

import java.io.Serializable;
import java.util.List;

public class Department implements Serializable, EntitiesDao<Department> {

    //Attributes
    private Integer id;
    private String name;

    //Constructor
    public Department(Integer id, String name) {
        setId(id);
        setName(name);
    }

    //Methods
    @Override
    public void insert(Department o) {

    }

    @Override
    public void update(Department o) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    //Accessors
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
