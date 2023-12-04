package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Seller extends Entity implements Serializable {

    //Attributes
    private Integer id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Double baseSalary;
    private Department department;

    //Constructor
    public Seller(Integer id, String name, String email, LocalDate birth_date, Double baseSalary, Department department) {
        setId(id);
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
                +"Department: " + getDepartment() + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
