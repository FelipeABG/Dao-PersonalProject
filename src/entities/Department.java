package entities;

import java.io.Serializable;
import java.util.Objects;

public class Department extends Entity implements Serializable {

    //Attributes
    private Integer id;
    private String name;

    //Constructor
    public Department(Integer id, String name) {
        setId(id);
        setName(name);
        setTableName("department");
    }

    //Methods
    @Override
    public String toString(){
        return "\n ID: " + getId() + "\n"
                + "Name: " + getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
