package com.danny.drools.entity;


/**
 * @author Danny
 * @Title: RiskInfoStatisticResult
 * @Copyright: Copyright (c) 2018
 * @Description: 风险信息统计结果
 * @Created on 2018-08-23 16:15:20
 */
public class RiskInfoStatisticResult extends BaseEntity{
    private String id;
    private String riskInfoStatisticRuleId;//风险信息统计规则id
    private String ruleKey;//规则标识key
    private int statisticResult;//统计结果

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public int getStatisticResult() {
        return statisticResult;
    }

    public void setStatisticResult(int statisticResult) {
        this.statisticResult = statisticResult;
    }

    public String getRiskInfoStatisticRuleId() {
        return riskInfoStatisticRuleId;
    }

    public void setRiskInfoStatisticRuleId(String riskInfoStatisticRuleId) {
        this.riskInfoStatisticRuleId = riskInfoStatisticRuleId;
    }
}
