package com.danny.drools.entity;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AntiFraudRecord
 * @Copyright: Copyright (c) 2016
 * @Description: 反欺诈记录
 * @Company: lxjr.com
 * @Created on 2016-12-23 18:26:40
 */
public class AntiFraudRecord {
    private String id;
    private String antiFraudRuleId;//反欺诈规则id
    private String userId;//用户id
    private boolean antiFraudResult=false;//反欺诈结果

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAntiFraudRuleId() {
        return antiFraudRuleId;
    }

    public void setAntiFraudRuleId(String antiFraudRuleId) {
        this.antiFraudRuleId = antiFraudRuleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAntiFraudResult() {
        return antiFraudResult;
    }

    public void setAntiFraudResult(boolean antiFraudResult) {
        this.antiFraudResult = antiFraudResult;
    }
}
