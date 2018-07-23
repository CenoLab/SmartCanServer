package com.iot.nero.smartcan.utils.dbtools;


import com.iot.nero.smartcan.utils.dbtools.connection.DBConnection;
import com.iot.nero.smartcan.utils.dbtools.entity.Condition;
import com.iot.nero.smartcan.utils.dbtools.entity.Conditions;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.iot.nero.smartcan.constant.CONSTANT.pInfo;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   上午9:49
 */
public class DataBase {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://123.150.131.187:3306/";
    private String username = "autocar";
    private String pd = "baby..520587";

    public DataBase(String driver, String url, String username, String pd) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.pd = pd;
    }

    public synchronized Integer createTable(String table, List<String> columns) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder sqlSchemaStringBuilder = new StringBuilder();
        sqlSchemaStringBuilder.append("create table if not  exists ");
        sqlSchemaStringBuilder.append(table);
        sqlSchemaStringBuilder.append("(");
        for(String s:columns){
            sqlSchemaStringBuilder.append(s);
            sqlSchemaStringBuilder.append(",");
        }
        sqlSchemaStringBuilder.delete(sqlSchemaStringBuilder.length() - 1, sqlSchemaStringBuilder.length());
        sqlSchemaStringBuilder.append(")Engine=InnoDB;");

        PreparedStatement preparedStatement = null;
        int i = 0;
        try {
            if(!sqlSchemaStringBuilder.toString().endsWith(";")){
                sqlSchemaStringBuilder.append(";");
            }
            pInfo("(SQL) "+sqlSchemaStringBuilder.toString());
            preparedStatement = (PreparedStatement) conn.prepareStatement(sqlSchemaStringBuilder.toString());
            i = preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return i;
    }

    public synchronized Integer insert(String table, List<String> columns, List<Object> values) throws SQLException, ClassNotFoundException {

        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder columnStringBuilder = new StringBuilder();
        for (String column : columns) {
            columnStringBuilder.append(column);
            columnStringBuilder.append(',');
        }
        columnStringBuilder.delete(columnStringBuilder.length() - 1, columnStringBuilder.length());

        StringBuilder valueStringBuilder = new StringBuilder();
        for (Object value : values) {
            valueStringBuilder.append('\''+String.valueOf(value)+'\'');
            valueStringBuilder.append(',');
        }
        valueStringBuilder.delete(valueStringBuilder.length() - 1, valueStringBuilder.length());

        StringBuilder sqlSchemaStringBuilder = new StringBuilder();
        sqlSchemaStringBuilder.append("insert into ");
        sqlSchemaStringBuilder.append(table);
        sqlSchemaStringBuilder.append('(');
        sqlSchemaStringBuilder.append(columnStringBuilder.toString());
        sqlSchemaStringBuilder.append(") values (");
        sqlSchemaStringBuilder.append(valueStringBuilder.toString());
        sqlSchemaStringBuilder.append(");");

        PreparedStatement preparedStatement = null;
        int i = 0;
        try {
            if(!sqlSchemaStringBuilder.toString().endsWith(";")){
                sqlSchemaStringBuilder.append(";");
            }
            pInfo("(SQL) "+sqlSchemaStringBuilder.toString());
            preparedStatement = (PreparedStatement) conn.prepareStatement(sqlSchemaStringBuilder.toString());
            preparedStatement = putData(preparedStatement);
            i = preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return i;
    }

    public List<String> desc(String table) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder descStringBuilder = new StringBuilder();
        descStringBuilder.append("desc ");
        descStringBuilder.append(table);
        PreparedStatement preparedStatementDesc = null;
        ResultSet reDesc = null;
        List<String> columns = new ArrayList<>();
        try {
            pInfo("(SQL) "+descStringBuilder.toString());
            preparedStatementDesc = conn.prepareStatement(descStringBuilder.toString());
            reDesc = preparedStatementDesc.executeQuery();
            int coldesc = reDesc.getMetaData().getColumnCount();

            while (reDesc.next()) {
                System.out.print(reDesc.getObject(1).toString() + " | ");
                columns.add(reDesc.getObject(1).toString());
            }
            System.out.println("");

        } finally {
            if (reDesc != null) {
                reDesc.close();
            }

            if (preparedStatementDesc != null) {
                preparedStatementDesc.close();
            }
        }
        return columns;
    }



    public  List<Map<String, Object>> select(String table) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder selectStringBuilder = new StringBuilder();
        selectStringBuilder.append("select *");
        selectStringBuilder.append(" from ");
        selectStringBuilder.append(table);

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {
            pInfo("(SQL) "+selectStringBuilder.toString());
            preparedStatement = (PreparedStatement) conn.prepareStatement(selectStringBuilder.toString());
            rs = preparedStatement.executeQuery();
            List<String> columns = desc(table);
            makeData(result,rs,columns);
        } finally {

            if (rs != null) {
                rs.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }

    public  List<Map<String, Object>> select(List<String> columns, String table, Conditions conditions) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder selectStringBuilder = new StringBuilder();
        selectStringBuilder.append("select ");
        for (String column : columns) {
            selectStringBuilder.append(column);
            selectStringBuilder.append(" as ");
            selectStringBuilder.append(column);
            selectStringBuilder.append(',');
        }
        selectStringBuilder.delete(selectStringBuilder.length() - 1, selectStringBuilder.length());
        selectStringBuilder.append(" from ");
        selectStringBuilder.append(table);
        selectStringBuilder.append(genConditions(conditions));

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try {preparedStatement = (PreparedStatement) conn.prepareStatement(selectStringBuilder.toString());
            pInfo("(SQL) "+selectStringBuilder.toString());
            rs = preparedStatement.executeQuery();
            makeData(result,rs,columns);
        } finally {

            if (rs != null) {
                rs.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return result;
    }



    public synchronized Integer update(List<String> columns, String table, Conditions conditions,Object ...values) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder updateStringBuilder = new StringBuilder();
        updateStringBuilder.append("update ");
        updateStringBuilder.append(table);
        updateStringBuilder.append(" set ");
        for (String column : columns) {
            updateStringBuilder.append(column);
            updateStringBuilder.append('=');
            updateStringBuilder.append('?');
            updateStringBuilder.append(',');
        }
        updateStringBuilder.delete(updateStringBuilder.length()-1,updateStringBuilder.length());

        updateStringBuilder.append(genConditions(conditions));

        PreparedStatement preparedStatement = null;
        Integer i = 0;
        try {
            pInfo("(SQL) "+updateStringBuilder.toString());
            preparedStatement = (PreparedStatement) conn.prepareStatement(updateStringBuilder.toString());
            preparedStatement = putData(preparedStatement,values);
            i = preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return i;
    }

    public Integer delete(String table,Conditions conditions) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        StringBuilder updateStringBuilder = new StringBuilder();
        updateStringBuilder.append("delete from ");
        updateStringBuilder.append(table);
        updateStringBuilder.append(genConditions(conditions));
        PreparedStatement preparedStatement = null;
        Integer i = 0;
        try {
            System.out.println(updateStringBuilder.toString());
            preparedStatement = (PreparedStatement) conn.prepareStatement(updateStringBuilder.toString());
            i = preparedStatement.executeUpdate();

        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return i;
    }

    private  StringBuilder genConditions(Conditions conditions){
        StringBuilder conditionStringBuilder = new StringBuilder();
        if (conditions!=null && !conditions.getConditionList().isEmpty()) {
            conditionStringBuilder.append(" where ");
            for (Condition condition : conditions.getConditionList()) {
                conditionStringBuilder.append(" ");
                conditionStringBuilder.append(condition.getKey());
                conditionStringBuilder.append(" ");
                conditionStringBuilder.append(condition.getExpress());
                conditionStringBuilder.append(" ");
                conditionStringBuilder.append(condition.getValue());
                conditionStringBuilder.append(" ");
                if (conditions.getConditionList().size() != 1) {
                    conditionStringBuilder.append(conditions.getCond());
                }
            }
            if (conditions.getConditionList().size() != 1) {
                conditionStringBuilder.delete(conditionStringBuilder.length() - conditions.getCond().length(), conditionStringBuilder.length());
            }
        }
        return conditionStringBuilder;
    }

    private  PreparedStatement putData(PreparedStatement preparedStatement, Object... values) throws SQLException {
        AtomicInteger index = new AtomicInteger(0);
        for (Object o : values) {
            if (o instanceof String) {
                preparedStatement.setString(index.incrementAndGet(), (String) o);
            }

            if (o instanceof Integer) {
                preparedStatement.setInt(index.incrementAndGet(), (Integer) o);
            }

            if (o instanceof Boolean) {
                preparedStatement.setBoolean(index.incrementAndGet(), (Boolean) o);
            }

            if (o instanceof Double) {
                preparedStatement.setDouble(index.incrementAndGet(), (Double) o);
            }

            if (o instanceof Float) {
                preparedStatement.setFloat(index.incrementAndGet(), (Float) o);
            }
            if (o instanceof Long) {
                preparedStatement.setLong(index.incrementAndGet(), (Long) o);
            }
            if (o instanceof BigDecimal) {
                preparedStatement.setBigDecimal(index.incrementAndGet(), (BigDecimal) o);
            }
            if (o instanceof Timestamp) {
                preparedStatement.setTimestamp(index.incrementAndGet(), (Timestamp) o);
            }
            if (o instanceof Date) {
                preparedStatement.setDate(index.incrementAndGet(), (Date) o);
            }

            //... others
        }
        return preparedStatement;
    }

    private List<Map<String,Object>> makeData(List<Map<String,Object>> data,ResultSet rs,List<String> columns) throws SQLException {
        int col = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            Map<String, Object> temp = new HashMap<String, Object>();
            for (int i = 1; i <= col; i++) {
                temp.put(columns.get(i-1),rs.getObject(i));
                System.out.print(rs.getObject(i).toString() + " | ");
            }
            data.add(temp);
            System.out.println("");
        }
        return data;
    }



    public  Integer tick() throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getConn(driver, url, username, pd);

        pInfo("(SQL) "+"select 1;");
        try( PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement("select 1;");
        ResultSet rs = preparedStatement.executeQuery()){

        }

        return 1;
    }

    public void close() throws SQLException {
        DBConnection.close(this.url);
    }


}
