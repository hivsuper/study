package com.bcp.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.springframework.util.StopWatch;

public abstract class TestRepository {
    private final BasicDataSource basicDataSource;

    TestRepository(BasicDataSource dataSource) {
        this.basicDataSource = dataSource;
    }

    public void insert() {
        try (Connection sourceConnection = basicDataSource.getConnection();
                Statement stmt = sourceConnection.createStatement()) {
            // Perform an initial count on the destination table.
            long countStart = 0;
            final String countTestBCPSql = "SELECT COUNT(*) FROM TestBCP";
            try (ResultSet rsRowCount = stmt.executeQuery(countTestBCPSql)) {
                rsRowCount.next();
                countStart = rsRowCount.getInt(1);
                getLogger().info("Starting row count = {}", countStart);
            }

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            this.doInsert();
            stopWatch.stop();
            getLogger().error("cost {}ms", stopWatch.getLastTaskTimeMillis());

            // Perform a final count on the destination
            // table to see how many rows were added.
            try (ResultSet rsRowCount = stmt.executeQuery(countTestBCPSql)) {
                rsRowCount.next();
                long countEnd = rsRowCount.getInt(1);
                getLogger().info("Ending row count = {}", countEnd);
                getLogger().info("{} rows were added.", (countEnd - countStart));
            }
        } catch (Exception e) {
            // Handle any errors that may have occurred.
            getLogger().error(e.getMessage(), e);
        }
    }

    protected abstract void doInsert();

    protected abstract Logger getLogger();

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }
}
