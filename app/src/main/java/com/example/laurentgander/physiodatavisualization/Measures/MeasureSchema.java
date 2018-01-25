package com.example.laurentgander.physiodatavisualization.Measures;


/**
 * Created by laurent.gander on 21/11/2017.
 */

public interface MeasureSchema {

    String TABLE_NAME = "measures";
    String COLUMNS_1 = "ID";
    String COLUMNS_2 = "CYNESTESIS_INDEX";
    String COLUMNS_3 = "HOURS_SLEEP";
    String COLUMNS_4 = "POSITION";
    String COLUMNS_5 = "COMMENTS";
    String COLUMNS_6 = "HEARTBEAT";
    String COLUMNS_7 = "STRESS_INDEX";
    String COLUMNS_8 = "DATE_MEASURES";
    String COLUMNS_9 = "HOURS_MEASURES";

    String[] tabColumns = new String[]{COLUMNS_1, COLUMNS_2, COLUMNS_3, COLUMNS_4, COLUMNS_5, COLUMNS_6, COLUMNS_7, COLUMNS_8, COLUMNS_9};

    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME
            + " (" + COLUMNS_1
            + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMNS_2
            + " INTEGER, "
            + COLUMNS_3
            + " INTEGER,"
            + COLUMNS_4
            + " INTEGER,"
            + COLUMNS_5
            + " TEXT,"
            + COLUMNS_6
            + " INTEGER, "
            + COLUMNS_7
            + " INTEGER, "
            + COLUMNS_8
            + " TEXT, "
            + COLUMNS_9
            + " TEXT)";

    String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
