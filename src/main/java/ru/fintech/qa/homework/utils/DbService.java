package ru.fintech.qa.homework.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbService {
    public static ResultSet executeQuery(final String sql) {
        ResultSet resultSet = null;
        Connection connection = new DbClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    public static int executeUpdate(final String sql) {
        int result = 0;
        Connection connection = new DbClient().getConnection();
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sql);
            connection.close();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
