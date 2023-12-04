package dao;

import db.DataBaseException;
import entities.Entity;
import java.sql.*;
import java.util.List;

public interface EntitiesDao<entity extends Entity>{

    //Abstract methods
    void insert(entity o);
    void update(entity o);
    List<entity> findAll();
    entity findById(Integer id);

    //Default methods
    default void deleteById(Integer id, Connection conn){
        String tableName = entity.getTableName();
        try{
            PreparedStatement statement = conn.prepareStatement("delete from ? where id = ?");

            statement.setString(1, tableName);
            statement.setInt(2, id);

            statement.executeUpdate();
            statement.close();
        }
        catch (SQLException e){
            throw new DataBaseException(e.getMessage());
        }
    }

}
