package com.iot.nero.smartcan.utils.dbtools.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author neroyang
 * Email  nerosoft@outlook.com
 * Date   2018/5/9
 * Time   下午1:39
 */
public class Conditions implements Serializable {

    private List<Condition> conditionList;
    private String cond;

    public Conditions() {
        cond = "";
        conditionList = new ArrayList<>();
    }

    public Conditions(List<Condition> conditionList, String cond) {
        this.conditionList = conditionList;
        this.cond = cond;
    }

    public void addCondition(Condition condition){
        this.conditionList.add(condition);
    }

    public List<Condition> getConditionList() {
        return conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    @Override
    public String toString() {
        return "Conditions{" +
                "conditionList=" + conditionList +
                ", cond='" + cond + '\'' +
                '}';
    }
}
