package com.iot.nero.smartcan.utils.dbtools.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   上午9:49
 */
public class DBConnection {

    private static SimpleConnectionPool simpleConnectionPool = new SimpleConnectionPool();

    public static Connection getConn(String driver,String url,String username,String password) throws ClassNotFoundException, SQLException {
        if(simpleConnectionPool.getConnection(url) == null) {
            Class.forName(driver);

                Connection connection = (Connection) DriverManager.getConnection(url, username, password);
                simpleConnectionPool.addConnection(url, new CountableConnection(connection));
                return simpleConnectionPool.getConnection(url).getConnection();

        }
        return simpleConnectionPool.getConnection(url).getConnection();
    }

    public static void close(String url) throws SQLException {
        simpleConnectionPool.closeConnection(url);
    }
}
