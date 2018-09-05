package com.danny.drools.points;

/**
 * @author Danny
 * @Title: PointDomain
 * @Description: 积分计算对象
 * @Created on 2018-09-05 18:03:32
 */
public class PointDomain {
    // 用户名
    private String userName;
    // 用户当前积分
    private int point;
    // 当日购物总次数
    private int buyNums;
    // 当日购物总金额
    private double buyMoney;
    // 当日退货总次数
    private int backNums;
    // 当日退货总金额
    private double backMoney;

    public int addPoint(int addPoint) {
        point += addPoint;
        return point;
    }

    public int subtractPoint(int subtractPoint) {
        point -= subtractPoint;
        return point;
    }

    public String getUserName() {
        return userName;
    }

    public PointDomain setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public int getPoint() {
        return point;
    }

    public PointDomain setPoint(int point) {
        this.point = point;
        return this;
    }

    public int getBuyNums() {
        return buyNums;
    }

    public PointDomain setBuyNums(int buyNums) {
        this.buyNums = buyNums;
        return this;
    }

    public int getBackNums() {
        return backNums;
    }

    public PointDomain setBackNums(int backNums) {
        this.backNums = backNums;
        return this;
    }

    public double getBuyMoney() {
        return buyMoney;
    }

    public PointDomain setBuyMoney(double buyMoney) {
        this.buyMoney = buyMoney;
        return this;
    }

    public double getBackMoney() {
        return backMoney;
    }

    public PointDomain setBackMoney(double backMoney) {
        this.backMoney = backMoney;
        return this;
    }

    @Override
    public String toString() {
        return userName;
    }


}
