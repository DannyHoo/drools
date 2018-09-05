package com.danny.drools.entity;

/**
 * @author Danny
 * @Title: AntiFraudModel
 * @Description: 反欺诈模型
 * @Created on 2018-08-23 15:26:20
 */
public class AntiFraudModel  extends BaseEntity{
    private String id;
    private String antiFraudModelName;//反欺诈模型名称
    private String productId;//所属产品
    private String triggerPosition;//触发动作/执行位置(枚举)。枚举，在什么动作或场景下触发反欺诈动作，比如APPLY(进件后)，WithDraw(提现前)等。

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAntiFraudModelName() {
        return antiFraudModelName;
    }

    public void setAntiFraudModelName(String antiFraudModelName) {
        this.antiFraudModelName = antiFraudModelName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTriggerPosition() {
        return triggerPosition;
    }

    public void setTriggerPosition(String triggerPosition) {
        this.triggerPosition = triggerPosition;
    }
}
