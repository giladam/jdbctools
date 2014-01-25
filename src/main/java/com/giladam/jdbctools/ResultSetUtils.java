package com.giladam.jdbctools;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Some utility methods for dealing with null values in JDBC ResultSets.
 *
 * @author Gil Adam
 *
 */
public class ResultSetUtils {

    private ResultSetUtils() {
        //Do not instantiate because it's all static methods.
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Boolean getNullableBoolean(ResultSet resultSet, String columnLabel) throws SQLException {
        boolean value = resultSet.getBoolean(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Boolean getNullableBoolean(ResultSet resultSet, int columnIndex) throws SQLException {
        boolean value = resultSet.getBoolean(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Byte getNullableByte(ResultSet resultSet, String columnLabel) throws SQLException {
        byte value = resultSet.getByte(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Byte getNullableByte(ResultSet resultSet, int columnIndex) throws SQLException {
        byte value = resultSet.getByte(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Short getNullableShort(ResultSet resultSet, String columnLabel) throws SQLException {
        short value = resultSet.getShort(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Short getNullableShort(ResultSet resultSet, int columnIndex) throws SQLException {
        short value = resultSet.getShort(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Integer getNullableInteger(ResultSet resultSet, String columnLabel) throws SQLException {
        int value = resultSet.getInt(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Integer getNullableInteger(ResultSet resultSet, int columnIndex) throws SQLException {
        int value = resultSet.getInt(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Long getNullableLong(ResultSet resultSet, String columnLabel) throws SQLException {
        long value = resultSet.getLong(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Long getNullableLong(ResultSet resultSet, int columnIndex) throws SQLException {
        long value = resultSet.getLong(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Float getNullableFloat(ResultSet resultSet, String columnLabel) throws SQLException {
    	float value = resultSet.getFloat(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Float getNullableFloat(ResultSet resultSet, int columnIndex) throws SQLException {
    	float value = resultSet.getFloat(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnLabel The label of the column.
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnLabel is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Double getNullableDouble(ResultSet resultSet, String columnLabel) throws SQLException {
    	double value = resultSet.getDouble(columnLabel);
        return resultSet.wasNull() ? null : value;
    }


    /**
     * Returns the value of the specified column from the ResultSet or null if the value is nullable and was null.
     * @param resultSet The ResultSet from which to retrieve the column.
     * @param columnIndex The numerical index of the column (starting with 1 for the first column).
     * @return The value for the column or null if it was null.
     * @throws SQLException If the columnIndex is not valid; if a database access error occurs or this method is called on a closed result set.
     */
    public static Double getNullableDouble(ResultSet resultSet, int columnIndex) throws SQLException {
        double value = resultSet.getDouble(columnIndex);
        return resultSet.wasNull() ? null : value;
    }


}
