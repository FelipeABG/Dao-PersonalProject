package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public abstract class DB {

    //Static Attributes
    private static Connection conn = null;

    //Static methods
    private static Properties getProperties(){
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(conn == null){
            try {
                Properties props = getProperties();
                String url = props.getProperty("url");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                throw new DataBaseException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            }
            catch (SQLException e){
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement statement){
        if(statement != null){
            try {
                statement.close();
            }
            catch (SQLException e){
                throw new DataBaseException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet result){
        if(result != null){
            try {
                result.close();
            }
            catch (SQLException e){
                throw new DataBaseException(e.getMessage());
            }
        }
    }

}
