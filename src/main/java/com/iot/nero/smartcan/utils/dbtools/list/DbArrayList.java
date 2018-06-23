package com.iot.nero.smartcan.utils.dbtools.list;

import java.util.ArrayList;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   下午2:44
 */
public class DbArrayList<T> extends ArrayList<T> {

    public DbArrayList(Object ...values) {
        for(Object value:values){
            this.add((T) value);
        }
    }
}
