package com.danny.drools.entity;

/**
 * @author huyuyang@lxfintech.com
 * @Title: RiskInfoStatisticRule
 * @Copyright: Copyright (c) 2016
 * @Description: 风险信息统计规则
 * @Company: lxjr.com
 * @Created on 2016-12-23 16:12:31
 */
public class RiskInfoStatisticRule  extends BaseEntity{
    private String id;
    private String ruleKey;//规则标识key
    private String statisticGrammer;//统计语法，json格式，需要由一个解析器解析成sql语句
    private long staticticCycle;//统计周期，单位：秒

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

    public String getStatisticGrammer() {
        return statisticGrammer;
    }

    public void setStatisticGrammer(String statisticGrammer) {
        this.statisticGrammer = statisticGrammer;
    }

    public long getStaticticCycle() {
        return staticticCycle;
    }

    public void setStaticticCycle(long staticticCycle) {
        this.staticticCycle = staticticCycle;
    }
}
