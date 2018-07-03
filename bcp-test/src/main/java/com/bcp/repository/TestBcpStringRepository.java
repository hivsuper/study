package com.bcp.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcp.jdbc.SQLServerBulkStringRecord;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;

@Named
public class TestBcpStringRepository extends TestRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestBcpStringRepository.class);
    public AtomicInteger startIndex = new AtomicInteger(1);

    @Inject
    public TestBcpStringRepository(BasicDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doInsert() {
        List<Object[]> list = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            list.add(new Object[] { String.valueOf(startIndex.getAndIncrement()), 2, 3, 4, 5, "6", 7.0001, 8.02, 9.99,
                    "2018-07-02", "2018-07-02 00:00:00.000" });
        }
        SQLServerBulkStringRecord record = new SQLServerBulkStringRecord(list);

        try {
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
                    bulkCopy.writeToServer(record);
                } catch (Exception e) {
                    // Handle any errors that may have occurred.
                    LOGGER.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            // Handle any errors that may have occurred.
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}