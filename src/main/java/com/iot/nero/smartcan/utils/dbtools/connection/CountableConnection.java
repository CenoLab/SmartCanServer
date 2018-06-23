package com.iot.nero.smartcan.utils.dbtools.connection;

import java.io.Serializable;
import java.sql.Connection;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/10
 * Time   上午11:40
 */
public class CountableConnection implements Serializable {
    private Integer connectionCount = 0;
    private Connection connection;

    public CountableConnection(Connection connection) {
        this.connectionCount +=1;
        this.connection = connection;
    }

    public Integer getConnectionCount() {
        return connectionCount;
    }

    public void setConnectionCount(Integer connectionCount) {
        this.connectionCount = connectionCount;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "CountableConnection{" +
                "connectionCount=" + connectionCount +
                ", connection=" + connection +
                '}';
    }

    public void addConn() {
        connectionCount+=1;
    }

    public void closeConn() {
        connectionCount-=1;
    }
}
