package com.danny.drools.antifraud;

import com.danny.drools.entity.AntiFraudRecord;
import com.danny.drools.entity.AntiFraudRule;
import com.danny.drools.entity.RiskInfoStatisticResult;
import com.danny.drools.entity.RiskInfoStatisticRule;
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
 * @Description: 反欺诈规则测试（执行drl规则）
 * 前提条件
 * 反欺诈、授信的基础就是数据，所以要建立在已经有基础数据（设备信息、手机号信息、用户行为信息等）的基础上。
 * 反欺诈流程
 *    1、配置风险信息统计规则（风险信息统计规则表）
 *    2、把统计规则中的统计语法解析成sql语句并执行。（定时跑批；或者事件触发）
 *       执行结果保存到风险信息统计结果中（风险信息统计结果表）
 *    3、事件触发后，开始执行反欺诈模块（具体什么时候触发可以由工作流控制）
 *          3-1、根据当前产品和触发动作查询当前反欺诈模型关联的所有反欺诈规则
 *          3-2、把风险信息统计结果与反欺诈规则逐一对比，由Drools引擎执行。需要传入规则实体、风险信息统计结果实体、反欺诈结果实体。
 *          3-3、最终每个规则的比对结果可以从反欺诈结果实体中获得。
 * @Created on 2018-08-23 16:02:17
 */
public class ExecutorByDRL {
    //风险信息统计规则
    private  static List<RiskInfoStatisticRule> riskInfoStatisticRuleList=new ArrayList<RiskInfoStatisticRule>();
    //风险信息统计结果
    private static List<RiskInfoStatisticResult> riskInfoStatisticResultList=new ArrayList<RiskInfoStatisticResult>();
    //反欺诈规则
    private static List<AntiFraudRule> antiFraudRuleList=new ArrayList<AntiFraudRule>();
    //每个反欺诈规则执行的记录
    private static List<AntiFraudRecord> antiFraudRecordList=new ArrayList<AntiFraudRecord>();

    public static void main(String[] args) {
        setRiskInfoStatisticRuleList();
        runRiskInfoStatisticRuleList(riskInfoStatisticRuleList);
        setAntiFraudRuleList();

        int i=0;

        //依次执行每种【风险统计结果】对应的【反欺诈规则】
        for (AntiFraudRule antiFraudRule:antiFraudRuleList) {
            //获取当前反欺诈规则的执行内容
            String rule="";
            //获取当前反欺诈规则对应的风险信息统计结果
            RiskInfoStatisticResult riskInfoStatisticResult=new RiskInfoStatisticResult();
            for(RiskInfoStatisticResult r:riskInfoStatisticResultList){
                if(r.getRiskInfoStatisticRuleId().equals(antiFraudRule.getRiskInfoStatisticRuleId())){
                    riskInfoStatisticResult=r;
                    break;
                }
            }

            //开始执行反欺诈
            AntiFraudRecord antiFraudRecord=new AntiFraudRecord();
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");
            kSession.insert(riskInfoStatisticResult);
            kSession.insert(antiFraudRecord);
            System.out.println("开始执行规则——"+antiFraudRule.getRuleKey());
            kSession.fireAllRules(new RuleNameEqualsAgendaFilter(antiFraudRule.getRuleKey()));
            antiFraudRecordList.add(antiFraudRecord);//保存每条反欺诈结果

            i++;
        }
        boolean result=true;
        for (AntiFraudRecord antiFraudRecord:antiFraudRecordList){
            if(antiFraudRecord.isAntiFraudResult()==false){
                result=false;
                break;
            }
        }
        System.out.println("计划执行规则条数:"+antiFraudRuleList.size()+";实际执行规则条数:"+i+"最终结果："+(result?"通过":"不通过"));
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
        riskInfoStatisticResult1.setRuleKey("one_day_login_number");
        riskInfoStatisticResult1.setStatisticResult(30);//当前用户一天之内登录次数为20次

        RiskInfoStatisticResult riskInfoStatisticResult2=new RiskInfoStatisticResult();
        riskInfoStatisticResult2.setId("2");
        riskInfoStatisticResult2.setRiskInfoStatisticRuleId("2");
        riskInfoStatisticResult2.setRuleKey("one_day_ip_number");
        riskInfoStatisticResult2.setStatisticResult(10);//当前设备一天之内使用ip数量为20

        riskInfoStatisticResultList.add(riskInfoStatisticResult1);
        riskInfoStatisticResultList.add(riskInfoStatisticResult2);
    }

    /**
     * 3-1、根据当前产品和触发动作查询当前反欺诈模型关联的所有反欺诈规则
     */
    public static void setAntiFraudRuleList(){
        //根据当前产品和动作判断是否需要执行反欺诈模型，如果需要，查询出当前反欺诈模型下所有反欺诈规则
        //模拟查询出反欺诈规则
        AntiFraudRule antiFraudRule1=new AntiFraudRule();
        antiFraudRule1.setId("1");
        antiFraudRule1.setRiskInfoStatisticRuleId("1");//反欺诈规则对应风险信息统计规则id
        antiFraudRule1.setRuleKey("one_day_login_number");//规则标识，来自于对应的风险信息统计规则
        antiFraudRule1.setAntiFraudRuleName("用户一天内登录次数小于20");
        antiFraudRule1.setRuleContent("");//Drools语法规定的规则内容

        AntiFraudRule antiFraudRule2=new AntiFraudRule();
        antiFraudRule2.setId("2");
        antiFraudRule2.setRiskInfoStatisticRuleId("2");
        antiFraudRule2.setRuleKey("one_day_ip_number");
        antiFraudRule2.setAntiFraudRuleName("过去24小时设备使用IP数量小于10");
        antiFraudRule2.setRuleContent("");

        antiFraudRuleList.add(antiFraudRule1);
        antiFraudRuleList.add(antiFraudRule2);
    }
}
