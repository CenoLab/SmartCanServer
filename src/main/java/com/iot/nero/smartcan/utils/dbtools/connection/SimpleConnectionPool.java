package com.iot.nero.smartcan.utils.dbtools.connection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   下午2:56
 */
public class SimpleConnectionPool {

    private Map<String,CountableConnection> connectionList;

    public SimpleConnectionPool(Map<String, CountableConnection> connectionList) {
        this.connectionList = connectionList;
    }

    public SimpleConnectionPool() {
        connectionList = new HashMap<>();
    }

    public CountableConnection getConnection(String url){
        return connectionList.get(url);
    }
    public CountableConnection addConnection(String url,CountableConnection connection){
        return connectionList.put(url,connection);
    }

    public Map<String, CountableConnection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(Map<String, CountableConnection> connectionList) {
        this.connectionList = connectionList;
    }

    public void closeConnection(String url) throws SQLException {
        if(connectionList.containsKey(url)) {
            if(connectionList.get(url).getConnectionCount()==1) {
                this.connectionList.get(url).getConnection().close();
                this.connectionList.remove(url);
            }else {
                connectionList.get(url).closeConn();
            }
        }
    }

    @Override
    public String toString() {
        return "SimpleConnectionPool{" +
                "connectionList=" + connectionList +
                '}';
    }
}
