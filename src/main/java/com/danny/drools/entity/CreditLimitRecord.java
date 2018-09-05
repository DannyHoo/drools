package com.danny.drools.entity;

/**
 * @author Danny
 * @Title: CreditLimitRecord
 * @Copyright: Copyright (c) 2018
 * @Description:
 * @Created on 2018-08-26 14:16:32
 */
public class CreditLimitRecord {
    private String id;
    private String creditLimitRuleId;//授信规则id
    private String userId;//用户id
    private float creditLimitResult;//反欺诈结果,float类型的分数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditLimitRuleId() {
        return creditLimitRuleId;
    }

    public void setCreditLimitRuleId(String creditLimitRuleId) {
        this.creditLimitRuleId = creditLimitRuleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getCreditLimitResult() {
        return creditLimitResult;
    }

    public void setCreditLimitResult(float creditLimitResult) {
        this.creditLimitResult = creditLimitResult;
    }
}
