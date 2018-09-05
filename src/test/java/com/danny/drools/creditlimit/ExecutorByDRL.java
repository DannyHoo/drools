package com.danny.drools.creditlimit;

import com.danny.drools.entity.*;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Danny
 * @Title: ExecutorByDRL
 * @Copyright: Copyright (c) 2018
 * @Description: 授信规则测试（执行drl规则）
 * @Created on 2018-08-26 14:12:54
 */
public class ExecutorByDRL {
    //风险信息统计规则
    private  static List<RiskInfoStatisticRule> riskInfoStatisticRuleList=new ArrayList<RiskInfoStatisticRule>();
    //风险信息统计结果
    private static List<RiskInfoStatisticResult> riskInfoStatisticResultList=new ArrayList<RiskInfoStatisticResult>();
    //授信规则
    private static List<CreditLimitRule> creditLimitRuleList=new ArrayList<CreditLimitRule>();
    //每个授信规则执行的记录
    private static List<CreditLimitRecord> creditLimitRecordList=new ArrayList<CreditLimitRecord>();

    public static void main(String[] args) {
        setRiskInfoStatisticRuleList();
        runRiskInfoStatisticRuleList(riskInfoStatisticRuleList);
        setCreditLimitRuleList();

        int i=0;

        //依次执行每种【风险统计结果】对应的【授信规则】
        for (CreditLimitRule creditLimitRule:creditLimitRuleList) {
            //获取当前授信规则的执行内容
            String rule="";
            //获取当前反欺诈规则对应的风险信息统计结果
            RiskInfoStatisticResult riskInfoStatisticResult=new RiskInfoStatisticResult();
            for(RiskInfoStatisticResult r:riskInfoStatisticResultList){
                if(r.getRiskInfoStatisticRuleId().equals(creditLimitRule.getRiskInfoStatisticRuleId())){
                    riskInfoStatisticResult=r;
                    break;
                }
            }

            //开始执行反欺诈
            CreditLimitRecord creditLimitRecord=new CreditLimitRecord();
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            kSession.insert(riskInfoStatisticResult);
            kSession.insert(creditLimitRecord);
            System.out.println("开始执行规则——"+creditLimitRule.getRuleKey());
            kSession.fireAllRules(new RuleNameEqualsAgendaFilter(creditLimitRule.getRuleKey()));
            creditLimitRecordList.add(creditLimitRecord);//保存每条授信结果

            i++;
        }
        float result=0;
        for (CreditLimitRecord creditLimitRecord:creditLimitRecordList){
            result+=creditLimitRecord.getCreditLimitResult();
        }
        System.out.println("计划执行规则条数:"+creditLimitRuleList.size()+";实际执行规则条数:"+i+"最终结果："+result+"分");
    }

    /**
     * 1、配置风险信息统计规则（风险信息统计规则表）
     */
    public static void setRiskInfoStatisticRuleList(){
        RiskInfoStatisticRule riskInfoStatisticRule1=new RiskInfoStatisticRule();
        riskInfoStatisticRule1.setId("1");
        riskInfoStatisticRule1.setRuleKey("one_day_login_number");//
        riskInfoStatisticRule1.setStatisticGrammer("{\"selectField\":\"count(*)\",\"tableName\":\"t_event\",\"condition\":\"userId={1} and time between date_sub(now(),interval 24 hour) and now()\"}");//解析成select count(*) from t_event where userId={1} and time between date_sub(now(),interval 24 hour) and now()
        riskInfoStatisticRule1.setStaticticCycle(60);//一分钟执行一次
        RiskInfoStatisticRule riskInfoStatisticRule2=new RiskInfoStatisticRule();
        riskInfoStatisticRule2.setId("2");
        riskInfoStatisticRule2.setRuleKey("one_day_ip_number");//
        riskInfoStatisticRule2.setStatisticGrammer("{\"selectField\":\"count(*)\",\"tableName\":\"t_shebei\",\"condition\":\"ip={1} and time between date_sub(now(),interval 24 hour) and now()\"}");//解析成select count(*) from t_shebei where ip={1} and time between date_sub(now(),interval 24 hour) and now()
        riskInfoStatisticRule2.setStaticticCycle(60);//一分钟执行一次
        riskInfoStatisticRuleList.add(riskInfoStatisticRule1);
        riskInfoStatisticRuleList.add(riskInfoStatisticRule2);
    }

    /**
     * 2、把统计规则中的统计语法解析成sql语句并执行。（定时跑批；或者事件触发）
     * 执行结果保存到风险信息统计结果中（风险信息统计结果表）
     */
    public static void runRiskInfoStatisticRuleList(List<RiskInfoStatisticRule> riskInfoStatisticRuleList){
        //1、解析sql 略
        //2、模拟执行sql，得到风险信息结果
        RiskInfoStatisticResult riskInfoStatisticResult1=new RiskInfoStatisticResult();
        riskInfoStatisticResult1.setId("1");
        riskInfoStatisticResult1.setRiskInfoStatisticRuleId("1");
        riskInfoStatisticResult1.setRuleKey("three_month_consume");
        riskInfoStatisticResult1.setStatisticResult(20000);//当前用户近3个月消费总额

        RiskInfoStatisticResult riskInfoStatisticResult2=new RiskInfoStatisticResult();
        riskInfoStatisticResult2.setId("2");
        riskInfoStatisticResult2.setRiskInfoStatisticRuleId("2");
        riskInfoStatisticResult2.setRuleKey("three_month_night");
        riskInfoStatisticResult2.setStatisticResult(2);//最近3个月夜消费次数

        riskInfoStatisticResultList.add(riskInfoStatisticResult1);
        riskInfoStatisticResultList.add(riskInfoStatisticResult2);
    }

    /**
     * 3-1、根据当前产品和触发动作查询当前反欺诈模型关联的所有反欺诈规则
     */
    public static void setCreditLimitRuleList(){
        //根据当前产品查询出当前授信模型下所有授信规则
        //模拟查询出授信规则
        CreditLimitRule creditLimitRule1=new CreditLimitRule();
        creditLimitRule1.setId("1");
        creditLimitRule1.setRiskInfoStatisticRuleId("1");//授信规则对应风险信息统计规则id
        creditLimitRule1.setCreditLimitRuleName("近3个月消费总额大于10000");
        creditLimitRule1.setRuleKey("three_month_consume");
        creditLimitRule1.setRuleContent("");//Drools语法规定的规则内容

        CreditLimitRule creditLimitRule2=new CreditLimitRule();
        creditLimitRule2.setId("2");
        creditLimitRule2.setRiskInfoStatisticRuleId("2");//授信规则对应风险信息统计规则id
        creditLimitRule2.setCreditLimitRuleName("最近3个月夜消费次数小于10次");
        creditLimitRule2.setRuleKey("three_month_night");
        creditLimitRule2.setRuleContent("");//Drools语法规定的规则内容

        creditLimitRuleList.add(creditLimitRule1);
        creditLimitRuleList.add(creditLimitRule2);
    }
}
