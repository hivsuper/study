package com.bcp.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;

@Named
public class TestBcpCSVRepository extends TestRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBcpCSVRepository.class);

    @Inject
    public TestBcpCSVRepository(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doInsert() {
        SQLServerBulkCSVFileRecord fileRecord = null;
        try {
            // Get data from the source file by loading it into a class that implements
            // ISQLServerBulkRecord.
            // Here we are using the SQLServerBulkCSVFileRecord implementation to import the
            // example CSV file.
            fileRecord = new SQLServerBulkCSVFileRecord("C:\\Users\\sli25\\Desktop\\DOPC-8876\\TestBulkCSVExample.csv",
                    null, "\\|", false);

            // Set the metadata for each column to be copied.
            fileRecord.addColumnMetadata(1, null, java.sql.Types.NVARCHAR, 0, 0);
            fileRecord.addColumnMetadata(2, null, java.sql.Types.TINYINT, 3, 0);
            fileRecord.addColumnMetadata(3, null, java.sql.Types.SMALLINT, 5, 0);
            fileRecord.addColumnMetadata(4, null, java.sql.Types.INTEGER, 10, 0);
            fileRecord.addColumnMetadata(5, null, java.sql.Types.BIGINT, 19, 0);
            fileRecord.addColumnMetadata(6, null, java.sql.Types.NVARCHAR, 0, 0);
            fileRecord.addColumnMetadata(7, null, java.sql.Types.FLOAT, 53, 0);
            fileRecord.addColumnMetadata(8, null, java.sql.Types.DECIMAL, 18, 0);
            fileRecord.addColumnMetadata(9, null, java.sql.Types.NUMERIC, 18, 0);
            fileRecord.addColumnMetadata(10, null, java.sql.Types.DATE, 10, 0);
            fileRecord.addColumnMetadata(11, null, java.sql.Types.DATE, 0, 0);

            // Set up the bulk copy object.
            // Note that the column positions in the source
            // data reader match the column positions in
            // the destination table so there is no need to
            // map columns.
            BasicDataSource dataSource = getBasicDataSource();
            try (Connection destinationConnection = DriverManager.getConnection(dataSource.getUrl(),
                    dataSource.getUsername(), dataSource.getPassword());
                    SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(destinationConnection)) {

                bulkCopy.setDestinationTableName("TestBCP");
                try {
                    // Write from the source to the destination.
                    bulkCopy.writeToServer(fileRecord);
                } catch (Exception e) {
                    // Handle any errors that may have occurred.
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (fileRecord != null) {
                try {
                    fileRecord.close();
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}