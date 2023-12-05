package dao.implementation;

import dao.Dao;
import db.DB;
import db.DataBaseException;
import entities.Department;
import entities.Seller;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement("insert into seller " +
                    "(seller_name, email, birth_date, base_salary, department_id) " +
                    "values " +
                    "(?, ?, ?, ?, ?);", statement.RETURN_GENERATED_KEYS);

            statement.setString(1, o.getName());
            statement.setString(2, o.getEmail());
            statement.setDate(3, o.getBirthDate());
            statement.setDouble(4, o.getBaseSalary());
            statement.setInt(5, o.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();

            if(rowsAffected > 0){
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    o.setId(id);
                }
            }
            else{
                throw new DataBaseException("No rows affected.");
            }

        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void update(Seller o) {
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement("""
                    update seller set\s
                    seller_name = ?, email = ?, birth_date = ?, base_salary = ?, department_id = ?\s
                    where id = ?
                    """);

            statement.setString(1, o.getName());
            statement.setString(2, o.getEmail());
            statement.setDate(3, o.getBirthDate());
            statement.setDouble(4, o.getBaseSalary());
            statement.setInt(5,o.getDepartment().getId());
            statement.setInt(6,o.getId());

            statement.executeUpdate();

        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Seller> list = new ArrayList<>();
        Map<Integer, Department> map = new HashMap<>();

        try {
            statement = conn.prepareStatement("select * from " +
                    "seller join department " +
                    "on seller.department_id = department.id ");

            result = statement.executeQuery();

            while (result.next()){
                Department dp = map.get(result.getInt("department_id"));

                if(dp == null){
                    dp = instanciateDepartment(result);
                    map.put(result.getInt("department_id"), dp);
                }

                list.add(instanciateSeller(result, dp));
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

        Seller sl = new Seller( sellerName, sellerEmail,
                sellerBirthDate, sellerSalary, department);

        sl.setId(sellerId);
        return sl;
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
