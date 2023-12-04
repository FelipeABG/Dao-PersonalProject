package dao.implementation;

import dao.Dao;
import db.DB;
import db.DataBaseException;
import entities.Department;
import entities.Seller;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class SellerDao implements Dao<Seller> {

    //Attributes
    private Connection conn;

    //Constructor
    public SellerDao(Connection conn){
        setConn(conn);
    }

    //Methods
    @Override
    public void insert(Seller o) {

    }

    @Override
    public void update(Seller o) {

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            statement = conn.prepareStatement("Select" +
                    " seller.*, department.department_name" +
                    " from seller join department" +
                    " on seller.department_id = department.id " +
                    " where seller.id = ?;");

            statement.setInt(1, id);

            result = statement.executeQuery();
            if(result.next()){
                String departmentName = result.getString("department_name");
                Integer departmentId = result.getInt("department_id");
                Department department = new Department(departmentId, departmentName);

                String sellerName = result.getString("seller_name");
                Integer sellerId = result.getInt("id");
                String sellerEmail = result.getString("email");
                Date sellerBirthDate = result.getDate("birth_date");
                Double sellerSalary = result.getDouble("base_salary");

                return new Seller(sellerId, sellerName, sellerEmail,
                        sellerBirthDate, sellerSalary, department);
            }
            return null;
        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(result);
        }
    }

    //Accessors
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
