package com.danny.drools.entity;

/**
 * @author Danny
 * @Title: CreditLimitRule
 * @Copyright: Copyright (c) 2018
 * @Description: 授信规则
 * @Created on 2018-08-18 16:34:50
 */
public class CreditLimitRule {
    private String id;//
    private String creditLimitRuleName;//授信规则名称
    private String creditLimitRuleGroupId;//授信规则组id
    private String riskInfoStatisticRuleId;//对应风险信息统计规则id
    private String ruleKey;//规则标识
    private String ruleContent;//规则内容，Drools语法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreditLimitRuleName() {
        return creditLimitRuleName;
    }

    public void setCreditLimitRuleName(String creditLimitRuleName) {
        this.creditLimitRuleName = creditLimitRuleName;
    }

    public String getCreditLimitRuleGroupId() {
        return creditLimitRuleGroupId;
    }

    public void setCreditLimitRuleGroupId(String creditLimitRuleGroupId) {
        this.creditLimitRuleGroupId = creditLimitRuleGroupId;
    }

    public String getRiskInfoStatisticRuleId() {
        return riskInfoStatisticRuleId;
    }

    public void setRiskInfoStatisticRuleId(String riskInfoStatisticRuleId) {
        this.riskInfoStatisticRuleId = riskInfoStatisticRuleId;
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
}
