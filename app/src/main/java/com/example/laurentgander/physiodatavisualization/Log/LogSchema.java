package com.example.laurentgander.physiodatavisualization.Log;

/**
 * Created by laurent.gander on 09.01.2018.
 */

public interface LogSchema {
    String TABLE_NAME = "logs";
    String COLUMNS_1 = "ID";
    String COLUMNS_2 = "TYPE";
    String COLUMNS_3 = "DATE";
    public String[] tabColumns = new String[]{COLUMNS_1, COLUMNS_2, COLUMNS_3};

    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME
            + " (" + COLUMNS_1
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMNS_2
            + " TEXT, "
            + COLUMNS_3
            + " TEXT)";

    String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
