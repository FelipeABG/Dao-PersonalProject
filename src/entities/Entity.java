package entities;

public abstract class Entity {

    //Static attributes
    private static String DB_TABLE_NAME;

    //Static methods
    protected static void setTableName(String name){
        DB_TABLE_NAME = name;
    }
    protected static String getTableName(){
        return DB_TABLE_NAME;
    }

}
