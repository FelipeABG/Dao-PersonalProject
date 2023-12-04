package dao;

import dao.implementation.DepartmentDao;
import dao.implementation.SellerDao;
import entities.Department;
import entities.Seller;
import java.sql.Connection;

public abstract class DaoFactory {

    //Static methods
    public static Dao<Seller> createSellerDao(Connection conn){
        return new SellerDao(conn);
    }

    public static Dao<Department> createDepartmentDao(Connection conn){
        return new DepartmentDao(conn);
    }
}
