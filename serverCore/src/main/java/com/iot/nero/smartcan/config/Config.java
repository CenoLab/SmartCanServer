package com.iot.nero.smartcan.config;


import com.iot.nero.smartcan.annotation.ConfigClass;
import com.iot.nero.smartcan.annotation.ConfigField;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/4
 * Time   下午12:49
 */

@ConfigClass
public class Config {


    @ConfigField("server.host")
    private String host = "localhost";
    @ConfigField("server.port")
    private Integer port = 1080;

    @ConfigField("sys.log.tablename")
    private String logTableName;

    @ConfigField("db.sql.driver")
    private String dbDriver;

    @ConfigField("db.sql.url")
    private String dbUrl;

    @ConfigField("db.sql.username")
    private String dbUsername;

    @ConfigField("db.sql.pwd")
    private String dbPwd;

    @ConfigField("db.sql.tickInterval")
    private Integer dbTickInterval;


    @ConfigField("sys.can.collectFrequency")
    private Integer collectFrequency = 1080;

    @ConfigField("sys.can.sendFrequency")
    private Integer sendFrequency = 1080;


    @ConfigField("plugin.path")
    private String pluginPath;


    public Config() {
    }

    public Config(String host, Integer port, String logTableName, String dbDriver, String dbUrl, String dbUsername, String dbPwd, Integer dbTickInterval, Integer collectFrequency, Integer sendFrequency, String pluginPath) {
        this.host = host;
        this.port = port;
        this.logTableName = logTableName;
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPwd = dbPwd;
        this.dbTickInterval = dbTickInterval;
        this.collectFrequency = collectFrequency;
        this.sendFrequency = sendFrequency;
        this.pluginPath = pluginPath;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLogTableName() {
        return logTableName;
    }

    public void setLogTableName(String logTableName) {
        this.logTableName = logTableName;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    public Integer getDbTickInterval() {
        return dbTickInterval;
    }

    public void setDbTickInterval(Integer dbTickInterval) {
        this.dbTickInterval = dbTickInterval;
    }

    public Integer getCollectFrequency() {
        return collectFrequency;
    }

    public void setCollectFrequency(Integer collectFrequency) {
        this.collectFrequency = collectFrequency;
    }

    public Integer getSendFrequency() {
        return sendFrequency;
    }

    public void setSendFrequency(Integer sendFrequency) {
        this.sendFrequency = sendFrequency;
    }

    public String getPluginPath() {
        return pluginPath;
    }

    public void setPluginPath(String pluginPath) {
        this.pluginPath = pluginPath;
    }

    @Override
    public String toString() {
        return "Config{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", logTableName='" + logTableName + '\'' +
                ", dbDriver='" + dbDriver + '\'' +
                ", dbUrl='" + dbUrl + '\'' +
                ", dbUsername='" + dbUsername + '\'' +
                ", dbPwd='" + dbPwd + '\'' +
                ", dbTickInterval=" + dbTickInterval +
                ", collectFrequency=" + collectFrequency +
                ", sendFrequency=" + sendFrequency +
                ", pluginPath='" + pluginPath + '\'' +
                '}';
    }
}
