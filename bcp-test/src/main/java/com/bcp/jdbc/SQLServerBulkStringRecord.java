package com.bcp.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class SQLServerBulkStringRecord implements ISQLServerBulkRecord {

    private class ColumnMetadata {
        String columnName;
        int columnType;
        int precision;
        int scale;

        ColumnMetadata(String name, int type, int precision, int scale) {
            columnName = name;
            columnType = type;
            this.precision = precision;
            this.scale = scale;
        }
    }

    private int counter = 0;
    private int rowCount = 1;
    private Map<Integer, ColumnMetadata> columnMetadata;
    private List<Object[]> data;

    public SQLServerBulkStringRecord(List<Object[]> data) {
        // add metadata
        columnMetadata = new HashMap<>();
        columnMetadata.put(1, new ColumnMetadata("UUID", java.sql.Types.NVARCHAR, 0, 0));
        columnMetadata.put(2, new ColumnMetadata("ColTinyint", java.sql.Types.NVARCHAR, 3, 0));
        columnMetadata.put(3, new ColumnMetadata("ColSmallint", java.sql.Types.NVARCHAR, 5, 0));
        columnMetadata.put(4, new ColumnMetadata("ColInt", java.sql.Types.NVARCHAR, 10, 0));
        columnMetadata.put(5, new ColumnMetadata("ColBigint", java.sql.Types.NVARCHAR, 19, 0));
        columnMetadata.put(6, new ColumnMetadata("ColString", java.sql.Types.NVARCHAR, 0, 0));
        columnMetadata.put(7, new ColumnMetadata("ColFloat", java.sql.Types.NVARCHAR, 53, 0));
        columnMetadata.put(8, new ColumnMetadata("ColDecimal", java.sql.Types.NVARCHAR, 18, 0));
        columnMetadata.put(9, new ColumnMetadata("ColNumeric", java.sql.Types.NVARCHAR, 18, 0));
        columnMetadata.put(10, new ColumnMetadata("ColDate", java.sql.Types.NVARCHAR, 10, 0));
        columnMetadata.put(11, new ColumnMetadata("ColDatetime", java.sql.Types.NVARCHAR, 0, 0));
        // add data
        this.rowCount = data.size();
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getColumnOrdinals()
     */
    @Override
    public Set<Integer> getColumnOrdinals() {
        return columnMetadata.keySet();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getColumnName(int)
     */
    @Override
    public String getColumnName(int column) {
        return columnMetadata.get(column).columnName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getColumnType(int)
     */
    @Override
    public int getColumnType(int column) {
        return columnMetadata.get(column).columnType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getPrecision(int)
     */
    @Override
    public int getPrecision(int column) {
        return columnMetadata.get(column).precision;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getScale(int)
     */
    @Override
    public int getScale(int column) {
        return columnMetadata.get(column).scale;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#isAutoIncrement(int)
     */
    @Override
    public boolean isAutoIncrement(int column) {
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#getRowData()
     */
    @Override
    public Object[] getRowData() throws SQLServerException {
        return data.get(counter++);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.microsoft.sqlserver.jdbc.ISQLServerBulkRecord#next()
     */
    @Override
    public boolean next() throws SQLServerException {
        if (counter < rowCount)
            return true;
        return false;
    }

    /**
     * reset the counter when using the interface for validating the data
     */
    public void reset() {
        counter = 0;
    }
}
