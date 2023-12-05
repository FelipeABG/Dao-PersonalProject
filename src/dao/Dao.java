package dao;

import db.DB;
import db.DataBaseException;
import entities.ModelEntity;
import java.sql.*;
import java.util.List;

public interface Dao<entity extends ModelEntity>{

    //Abstract methods
    void insert(entity o);
    void update(entity o);
    List<entity> findAll();
    entity findById(Integer id);

    //Default methods
    default void deleteById(Integer id){
        String tableName = entity.getTableName();
        Connection conn = DB.getConnection();
        try{
            PreparedStatement statement = conn.prepareStatement("delete from " +tableName + " where id = ?");

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
