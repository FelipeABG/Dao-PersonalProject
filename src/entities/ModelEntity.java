package entities;

public abstract class ModelEntity {

    //Attributes
    private static String DB_TABLE_NAME;
    private Integer id;

    //Methods
    public static String getTableName() {
        return DB_TABLE_NAME;
    }

    public static void setTableName(String name) {
        DB_TABLE_NAME = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
