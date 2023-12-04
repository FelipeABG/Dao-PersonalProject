package entities;

import java.io.Serializable;
import java.util.Objects;

public class Department extends ModelEntity implements Serializable{

    //Attributes
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
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    //Accessors
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
