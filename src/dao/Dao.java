package dao;

import db.DB;
import db.DataBaseException;
import entities.Department;
import entities.ModelEntity;
import entities.Seller;

import java.sql.*;
import java.util.List;

public interface Dao<entity extends ModelEntity>{

    //Abstract methods
    void insert(entity o);
    void update(entity o);
    List<entity> findAll();
    entity findById(Integer id);
    void deleteById(Integer id);

    //Default methods
     default Seller instanciateSeller(ResultSet result, Department department) throws SQLException{
        String sellerName = result.getString("seller_name");
        Integer sellerId = result.getInt("id");
        String sellerEmail = result.getString("email");
        Date sellerBirthDate = result.getDate("birth_date");
        Double sellerSalary = result.getDouble("base_salary");

        Seller sl = new Seller( sellerName, sellerEmail,
                sellerBirthDate, sellerSalary, department);

        sl.setId(sellerId);
        return sl;
    }

    default Department instanciateDepartment(ResultSet result) throws SQLException{
        String departmentName = result.getString("department_name");
        Integer departmentId = result.getInt("id");
        Department dp = new Department(departmentName);

        dp.setId(departmentId);
        return dp;
    }

}
