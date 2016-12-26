package com.danny.drools.entity;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AntiFraudRuleGroup
 * @Copyright: Copyright (c) 2016
 * @Description: 反欺诈规则组
 * @Company: lxjr.com
 * @Created on 2016-12-18 16:34:23
 */
public class AntiFraudRuleGroup extends BaseEntity{
    private String id;
    private String antiFraudModelId;//所属反欺诈模型id
    private String antiFraudRuleGroupName;//反欺诈规则组名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAntiFraudModelId() {
        return antiFraudModelId;
    }

    public void setAntiFraudModelId(String antiFraudModelId) {
        this.antiFraudModelId = antiFraudModelId;
    }

    public String getAntiFraudRuleGroupName() {
        return antiFraudRuleGroupName;
    }

    public void setAntiFraudRuleGroupName(String antiFraudRuleGroupName) {
        this.antiFraudRuleGroupName = antiFraudRuleGroupName;
    }
}
