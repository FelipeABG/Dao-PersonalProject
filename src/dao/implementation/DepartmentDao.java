package dao.implementation;

import dao.Dao;
import entities.Department;

import java.sql.Connection;
import java.util.List;

public class DepartmentDao implements Dao<Department> {

    //Attributes
    private Connection conn;

    //Constructor
    public DepartmentDao(Connection conn){
        setConn(conn);
    }

    //Methods
    @Override
    public void insert(Department o) {

    }

    @Override
    public void update(Department o) {

    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    //Accessors
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
