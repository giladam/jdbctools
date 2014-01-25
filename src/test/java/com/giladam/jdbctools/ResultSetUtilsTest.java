package com.giladam.jdbctools;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.util.Assert;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class ResultSetUtilsTest {

    private static final Logger log = LoggerFactory.getLogger(ResultSetUtilsTest.class);

    private static NamedParameterJdbcTemplate jdbcTemplate;

    private static String OK = "OK";

    private static String[] TEST_RECORD_NAMES = {"A", "B", "C"};

    private static final String CREATE_TABLE_DDL = "CREATE TABLE TestData (" +
                                                   "	ByteCol INT, " +
                                                   "	ByteColNull INT NULL, " +
                                                   "	BooleanCol BOOLEAN, " +
                                                   "	BooleanColNull BOOLEAN NULL, " +
                                                   "	ShortCol SMALLINT, " +
                                                   "	ShortColNull SMALLINT NULL, " +
                                                   "	IntCol INT, " +
                                                   "	IntColNull INT NULL, " +
                                                   "	LongCol BIGINT, " +
                                                   "	LongColNull BIGINT NULL, " +
                                                   "	FloatCol REAL, " +
                                                   "	FloatColNull REAL NULL, " +
                                                   "	DoubleCol DOUBLE, " +
                                                   "	DoubleColNull DOUBLE NULL, " +
                                                   "	TestRecordId IDENTITY NOT NULL PRIMARY KEY, " +
                                                   "	TestRecordName VARCHAR(16) NOT NULL, " +
                                                   "	CreatedTime TIMESTAMP NULL " +
                                                   ")";


    private static final String INSERT_TESTDATA_SQL =
                    "INSERT INTO TestData " +
                    "   (CreatedTime, TestRecordName, ByteCol, BooleanCol, ShortCol, IntCol, LongCol, FloatCol, DoubleCol) " +
                    "VALUES " +
                    "   (:CreatedTime, :TestRecordName, :ByteCol, :BooleanCol, :ShortCol, :IntCol, :LongCol, :FloatCol, :DoubleCol)";


    private static final List<String> SELECT_TESTDATA_COLUMNS = ImmutableList.of("TestRecordId",
                                                                                 "CreatedTime",
                                                                                 "TestRecordName",
                                                                                 "ByteCol",
                                                                                 "ByteColNull",
                                                                                 "BooleanCol",
                                                                                 "BooleanColNull",
                                                                                 "ShortCol",
                                                                                 "ShortColNull",
                                                                                 "IntCol",
                                                                                 "IntColNull",
                                                                                 "LongCol",
                                                                                 "LongColNull",
                                                                                 "FloatCol",
                                                                                 "FloatColNull",
                                                                                 "DoubleCol",
                                                                                 "DoubleColNull");


    private static final String SELECT_TESTDATA_SQL =   "SELECT " + Joiner.on(',').join(SELECT_TESTDATA_COLUMNS) + " " +
                                                        "FROM TestData " +
                                                        "WHERE TestRecordName = :TestRecordName";


    @BeforeClass
    public static void setupTestDatabase() throws ClassNotFoundException, SQLException, IOException {

        //Setup the JDBC Datasource:
        DataSource datasource = getDatasource();
        jdbcTemplate = new NamedParameterJdbcTemplate(datasource);

        log.info("Setting up the testing DB.");
        Stopwatch stopwatch = Stopwatch.createStarted();

        //Create the table:
        jdbcTemplate.update(CREATE_TABLE_DDL, Collections.<String,String>emptyMap());

        //Insert a few records of test data:
        for (String testRecordName : TEST_RECORD_NAMES) {
            jdbcTemplate.update(INSERT_TESTDATA_SQL, getTestDataParameters(testRecordName));
        }

        log.info("The testing DB was setup for testing in {}", stopwatch);
    }


    private static SqlParameterSource getTestDataParameters(String testRecordId) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("CreatedTime", new Date());
        params.addValue("TestRecordName", testRecordId);

        params.addValue("ByteCol", 64);
        params.addValue("BooleanCol", true);
        params.addValue("ShortCol", 1234);
        params.addValue("IntCol", 1234567);
        params.addValue("LongCol", 1234567890123456789L);
        params.addValue("FloatCol", 3.14f);
        params.addValue("DoubleCol", 3.141592653d);

        return params;
    }


    private static DataSource getDatasource() {

        JdbcDataSource ds = new JdbcDataSource();

        ds.setUrl("jdbc:h2:mem:ResultSetUtilsTest;DB_CLOSE_DELAY=-1");

        return ds;
    }


    private void testByQuerying(String testRecordName, RowMapper<String> assertingRowMapper) {

        List<String> results = jdbcTemplate.query(SELECT_TESTDATA_SQL,
                ImmutableMap.of("TestRecordName", testRecordName),
                assertingRowMapper);

        Assert.notEmpty(results, "Test Record [" + testRecordName + "] had empty results.");
    }


    @Test
    public void testGetNullableBoolean_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableBoolean(rs, "BooleanColNull"));
                Assert.notNull(ResultSetUtils.getNullableBoolean(rs, "BooleanCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableBoolean_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableBoolean(rs, SELECT_TESTDATA_COLUMNS.indexOf("BooleanColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableBoolean(rs, SELECT_TESTDATA_COLUMNS.indexOf("BooleanCol") + 1));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableByte_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableByte(rs, SELECT_TESTDATA_COLUMNS.indexOf("ByteColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableByte(rs, SELECT_TESTDATA_COLUMNS.indexOf("ByteCol") + 1));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableByte_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableByte(rs, "ByteColNull"));
                Assert.notNull(ResultSetUtils.getNullableByte(rs, "ByteCol"));
                return OK;
            }
        });
    }



    @Test
    public void testGetNullableShort_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableShort(rs, "ShortColNull"));
                Assert.notNull(ResultSetUtils.getNullableShort(rs, "ShortCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableShort_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableShort(rs, SELECT_TESTDATA_COLUMNS.indexOf("ShortColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableShort(rs, SELECT_TESTDATA_COLUMNS.indexOf("ShortCol") + 1));
                return OK;
            }
        });
    }



    @Test
    public void testGetNullableInteger_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableInteger(rs, "IntColNull"));
                Assert.notNull(ResultSetUtils.getNullableInteger(rs, "IntCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableInteger_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableInteger(rs, SELECT_TESTDATA_COLUMNS.indexOf("IntColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableInteger(rs, SELECT_TESTDATA_COLUMNS.indexOf("IntCol") + 1));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableLong_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableLong(rs, "LongColNull"));
                Assert.notNull(ResultSetUtils.getNullableLong(rs, "LongCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableLong_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableLong(rs, SELECT_TESTDATA_COLUMNS.indexOf("LongColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableLong(rs, SELECT_TESTDATA_COLUMNS.indexOf("LongCol") + 1));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableFloat_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableFloat(rs, "FloatColNull"));
                Assert.notNull(ResultSetUtils.getNullableFloat(rs, "FloatCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableFloat_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableFloat(rs, SELECT_TESTDATA_COLUMNS.indexOf("FloatColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableFloat(rs, SELECT_TESTDATA_COLUMNS.indexOf("FloatCol") + 1));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableDouble_ByLabel() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableDouble(rs, "DoubleColNull"));
                Assert.notNull(ResultSetUtils.getNullableDouble(rs, "DoubleCol"));
                return OK;
            }
        });
    }


    @Test
    public void testGetNullableDouble_ByIndex() {
        testByQuerying("A", new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                Assert.isNull(ResultSetUtils.getNullableDouble(rs, SELECT_TESTDATA_COLUMNS.indexOf("DoubleColNull") + 1));
                Assert.notNull(ResultSetUtils.getNullableDouble(rs, SELECT_TESTDATA_COLUMNS.indexOf("DoubleCol") + 1));
                return OK;
            }
        });
    }

}
