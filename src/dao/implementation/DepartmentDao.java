package dao.implementation;

import dao.Dao;
import db.DB;
import db.DataBaseException;
import entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("""
                    insert into department (department_name)\s
                    values (?);
                    """, statement.RETURN_GENERATED_KEYS);

            statement.setString(1,o.getName());

            int rows = statement.executeUpdate();

            if(rows > 0){
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    o.setId(result.getInt(1));
                }
            }
            else {
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
    public void update(Department o) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    """
                            update department 
                            set department_name = ? 
                            where id = ?; 
                        """);

            statement.setString(1,o.getName());
            statement.setInt(2, o.getId());

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
    public List<Department> findAll() {
        PreparedStatement statement = null;
        ResultSet result = null;
        List<Department> list = new ArrayList<>();

        try {

            statement = conn.prepareStatement("select * from department;");

            result = statement.executeQuery();

            while (result.next()){
                list.add(instanciateDepartment(result));
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
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("select * from department where id = ?;");
            statement.setInt(1,id);

            result = statement.executeQuery();

            if(result.next()){
                return instanciateDepartment(result);
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

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("""
                    delete from department where id = ?;
                    """);

            statement.setInt(1, id);

            int rows = statement.executeUpdate();

        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
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
