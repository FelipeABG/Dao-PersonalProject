package dao;

import dao.implementation.DepartmentDao;
import dao.implementation.SellerDao;
import db.DB;
import entities.Department;
import entities.Seller;
import java.sql.Connection;

public abstract class DaoFactory {

    //Static methods
    public static Dao<Seller> createSellerDao(){
        return new SellerDao(DB.getConnection());
    }

    public static Dao<Department> createDepartmentDao(){
        return new DepartmentDao(DB.getConnection());
    }
}
