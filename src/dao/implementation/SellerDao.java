package dao.implementation;

import dao.Dao;
import db.DB;
import db.DataBaseException;
import entities.Department;
import entities.Seller;
import java.sql.*;
import java.util.ArrayList;
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
                Department department = instanciateDepartment(result);
                return instanciateSeller(result, department);
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

    public List<Seller> findByDepartment(Department department){
        PreparedStatement statement= null;
        ResultSet result = null;
        List<Seller> list = new ArrayList<>();
        try{
            statement = conn.prepareStatement("select * from " +
                    "seller join department " +
                    "on seller.department_id = department.id " +
                    "where department_id = ?;");

            statement.setInt(1 ,department.getId());
            result = statement.executeQuery();

            while (result.next()){
                list.add(instanciateSeller(result, department));
            }
            return list;
        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeResultSet(result);
        }
    }

    //Auxiliary methods
    private Seller instanciateSeller(ResultSet result, Department department) throws SQLException{
        String sellerName = result.getString("seller_name");
        Integer sellerId = result.getInt("id");
        String sellerEmail = result.getString("email");
        Date sellerBirthDate = result.getDate("birth_date");
        Double sellerSalary = result.getDouble("base_salary");

        return new Seller(sellerId, sellerName, sellerEmail,
                sellerBirthDate, sellerSalary, department);
    }

    private Department instanciateDepartment(ResultSet result) throws SQLException{
        String departmentName = result.getString("department_name");
        Integer departmentId = result.getInt("department_id");
        return new Department(departmentId, departmentName);
    }

    //Accessors
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
