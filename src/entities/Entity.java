package entities;

public abstract class Entity {

    //Attributes
    private static String DB_TABLE_NAME;
    private Integer id;

    //Methods
    public static String getTableName() {
        return DB_TABLE_NAME;
    }

    public static void setTableName(String DB_TABLE_NAME) {
        this.DB_TABLE_NAME = DB_TABLE_NAME;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
