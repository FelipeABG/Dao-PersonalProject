package dao.implementation;

import dao.Dao;
import db.DataBaseException;
import entities.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try {
            PreparedStatement statement = conn.prepareStatement("Select" +
                    "seller.*, department.name" +
                    "from seller join department" +
                    "on seller.department_id = department.id " +
                    "where seller.id = ?;");

            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            if(result.next()){

            }
            return null;
        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
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
