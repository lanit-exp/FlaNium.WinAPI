package FlaNium.WinAPI.elements.enums;

/**
 * Contains values that specify whether data in a table should be read primarily by row or by column.
 */
public enum  RowOrColumnMajor {

    /**
     * Data in the table should be read row by row.
     */
    ROW_MAJOR,

    /**
     * Data in the table should be read column by column.
     */
    COLUMN_MAJOR,

    /**
     * The best way to present the data is indeterminate.
     */
    INDETERMINATE;

    public static RowOrColumnMajor getEnum(String value){
        switch (value){
            case "RowMajor": return ROW_MAJOR;
            case "ColumnMajor": return COLUMN_MAJOR;
            case "Indeterminate": return INDETERMINATE;
            default: return null;
        }
    }
}
