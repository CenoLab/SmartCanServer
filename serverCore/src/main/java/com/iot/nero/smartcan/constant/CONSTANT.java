package com.iot.nero.smartcan.constant;

import java.util.Date;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/6/5
 * Time   下午3:37
 */
public class CONSTANT {
    public static final String VERSION = "2.1.3";
    public static final String WEB_SITE = "https://www.cenocloud.com";
    public static final String INDEX_NODE = "智能网联车数据采集 Server";
    public static final String COPY_RIGHT = WEB_SITE+" All Right Reserved";
    public static final String CLIENT_OFFLINE = "客户端掉线";

    public static void printNdfsInfo() {
        System.out.println("   _____                         .      ___               ");
        System.out.println("  (      , _ , _     ___  .___  _/_   .'   \\   ___  , __  ");
        System.out.println("   `--.  |' `|' `.  /   ` /   \\  |    |       /   ` |'  `.");
        System.out.println("      |  |   |   | |    | |   '  |    |      |    | |    |");
        System.out.println(" \\___.'  /   '   / `.__/| /      \\__/  `.__, `.__/| /    |");
        System.out.println("                                                          ");
        System.out.println(INDEX_NODE +"-"+ VERSION);
        System.out.println(COPY_RIGHT);
    }

    public static void pInfo(Object info) {
        System.out.println("[" + new Date().toString() + "] " + info.toString());
    }


    public static final String INVALID_HOST_NAME = "hostname 不合法";
    public static final String INVALID_PORT = "port 不合法";


    public static final String PACKAGE_BROKEN = "不完整的数据包";
    public static final String START_SYMBOL_INCORRECT = "未知的起始符";

    public static final String LOG_TYPE_WARNING = "warning";
    public static final String LOG_TYPE_ERROR = "error";
    public static final String LOG_TYPE_INFO = "info";
    public static final String LOG_TYPE_DEBUG = "debug";


    public static final String LOG_MESSAGE_UNKNOWN_LOGIN_CAR = "未注册的车辆";
    public static final String LOG_MESSAGE_LOGIN_CAR = "车辆登陆";
    public static final String LOG_MESSAGE_TOKEN_LOGIN_INCORRECT = "错误的token";

    public static final String PLUGIN_PATH_ERROR = "错误的插件路径";
    public static final String FIND_PLUGIN = "find plugin：";


    public static final String FREE_LICENSE = "Your license is a free license, which means a maximum of 20 client connections are allowed. If this does not meet your needs, please purchase a commercial license at "+WEB_SITE;
    public static final String ENTERPRISE_LICENSE = "Your license is a commercial license, thank you for your use, we provide you with 7×24 product support, see "+WEB_SITE+" for details.";
    public static final String TRIAL_LICENSE = "Your license is a trial license, which will expire in 14 days. To ensure your normal use, you are advised to purchase a commercial license. See "+WEB_SITE+" for details.";
}
