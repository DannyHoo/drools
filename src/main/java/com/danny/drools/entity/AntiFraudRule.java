package com.danny.drools.entity;

import java.util.Date;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AntiFraudRule
 * @Copyright: Copyright (c) 2016
 * @Description: 反欺诈规则
 * @Company: lxjr.com
 * @Created on 2016-12-18 16:34:09
 */
public class AntiFraudRule extends BaseEntity{
    private String id;//
    private String antiFraudRuleName;//反欺诈规则名称
    private String antiFraudRuleGroupId;//所属反欺诈规则组id
    private String riskInfoStatisticRuleId;//对应风险信息统计规则id
    private String ruleKey;//规则标识
    private String ruleContent;//规则内容，Drools语法
    private int priority;//优先级

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAntiFraudRuleGroupId() {
        return antiFraudRuleGroupId;
    }

    public void setAntiFraudRuleGroupId(String antiFraudRuleGroupId) {
        this.antiFraudRuleGroupId = antiFraudRuleGroupId;
    }

    public String getAntiFraudRuleName() {
        return antiFraudRuleName;
    }

    public void setAntiFraudRuleName(String antiFraudRuleName) {
        this.antiFraudRuleName = antiFraudRuleName;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public String getRiskInfoStatisticRuleId() {
        return riskInfoStatisticRuleId;
    }

    public void setRiskInfoStatisticRuleId(String riskInfoStatisticRuleId) {
        this.riskInfoStatisticRuleId = riskInfoStatisticRuleId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
