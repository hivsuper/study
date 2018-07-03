package com.bcp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Named
public class TestInsertRepository extends TestRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestInsertRepository.class);
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String intsertSql = "INSERT INTO TestBCP(UUID,ColTinyint,ColSmallint,ColInt,ColBigint,ColString,ColFloat,ColDecimal,ColNumeric,ColDate,ColDatetime) VALUES(:UUID, :ColTinyint, :ColSmallint, :ColInt, :ColBigint, :ColString, :ColFloat, :ColDecimal, :ColNumeric, :ColDate, :ColDatetime)";
    private final AtomicInteger startIndex = new AtomicInteger(1);

    @Inject
    public TestInsertRepository(BasicDataSource dataSource) {
        super(dataSource);
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    protected void doInsert() {
        List<Map<String, Object>> paramMapList = new ArrayList<>();
        for (int index = 0; index < 100; index++) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("UUID", String.valueOf(startIndex.getAndIncrement()));
            paramMap.put("ColTinyint", "2");
            paramMap.put("ColSmallint", "3");
            paramMap.put("ColInt", "4");
            paramMap.put("ColBigint", "5");
            paramMap.put("ColString", "6");
            paramMap.put("ColFloat", "7.0001");
            paramMap.put("ColDecimal", "8.02");
            paramMap.put("ColNumeric", "9.99");
            paramMap.put("ColDate", "2018-07-02");
            paramMap.put("ColDatetime", "2018-07-02 00:00:00");
            paramMapList.add(paramMap);
        }
        @SuppressWarnings("unchecked")
        Map<String, ?>[] array = new HashMap[paramMapList.size()];
        jdbcTemplate.batchUpdate(intsertSql, paramMapList.toArray(array));
    }

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }
}
