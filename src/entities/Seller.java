package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Seller extends ModelEntity implements Serializable {

    //Attributes
    private String name;
    private String email;
    private Date birthDate;
    private Double baseSalary;
    private Department department;

    //Constructor
    public Seller( String name, String email, Date birth_date, Double baseSalary, Department department) {
        setName(name);
        setEmail(email);
        setBirthDate(birth_date);
        setBaseSalary(baseSalary);
        setDepartment(department);
        setTableName("seller");
    }

    //Methods
    @Override
    public String toString(){
        return "ID: " + getId() + "\n"
                + "Name: " + getName() + "\n"
                + "Email: " + getEmail() + "\n"
                + "Birth date: " + getBirthDate() + "\n"
                + "base salary: " + getBaseSalary() + "\n"
                +"Department ID: " + getDepartment().getId() + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(getId(), seller.getId());
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
