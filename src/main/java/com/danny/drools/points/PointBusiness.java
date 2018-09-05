package com.danny.drools.points;

import com.danny.business.points.*;
import com.danny.business.points.PointDomain;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * @author Danny
 * @Title: PointBusiness
 * @Description:
 * @Created on 2018-09-05 22:49:55
 */
public class PointBusiness {

    public static void main(String[] args) {
        // 用户Danny初始积分为100，当日购物次数为5次，购物金额为558元，退货次数为0，退货金额为0
        com.danny.business.points.PointDomain pointDomain1 = initPointDomain("Danny", 100, 5, 588, 0, 0);
        // 用户Jackson初始积分为100，当日购物次数为12次，购物金额为1288元，退货次数为2，退货金额为128
        com.danny.business.points.PointDomain pointDomain2 = initPointDomain("Jackson", 100, 12, 1288, 2, 128);
        // 用户Lucy初始积分为100，当日购物次数为0次，购物金额为0元，退货次数为3，退货金额为239
        com.danny.business.points.PointDomain pointDomain3 = initPointDomain("Lucy", 100, 0, 0, 3, 239);
        // 为Danny、Jackson、Lucy三个用户计算积分

        KieServices kieServices= KieServices.Factory.get();
        KieContainer kieContainer=kieServices.getKieClasspathContainer();
        KieSession kieSession=kieContainer.newKieSession("ksession-rules");
        kieSession.insert(pointDomain1);
        kieSession.insert(pointDomain2);
        kieSession.insert(pointDomain3);
        System.out.println("开始执行规则");
        kieSession.fireAllRules(
                new RuleNameStartsWithAgendaFilter("point_")
        );
        kieSession.dispose();

        System.out.println("用户" + pointDomain1 + "的总积分为：" + pointDomain1.getPoint());
        System.out.println("用户" + pointDomain2 + "的总积分为：" + pointDomain2.getPoint());
        System.out.println("用户" + pointDomain3 + "的总积分为：" + pointDomain3.getPoint());
    }

    /**
     * 初始化PointDomain实例
     *
     * @param userName
     * @param point
     * @param buyNums
     * @param buyMoney
     * @param backNums
     * @param backMoney
     * @return
     */
    private static com.danny.business.points.PointDomain initPointDomain(String userName, int point, int buyNums, double buyMoney, int backNums, double backMoney) {
        return new PointDomain()
                .setUserName(userName)
                .setPoint(point)
                .setBuyNums(buyNums)
                .setBuyMoney(buyMoney)
                .setBackNums(backNums)
                .setBackMoney(backMoney);
    }
}
