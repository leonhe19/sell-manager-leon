package com.xisuo.sellmanager.entity;

import java.util.Date;

public class VoOrder {

    private long id;
    private long userId;
    private String userName;//创建者名字
    private long customerId;//客户id
    private String customerName;//客户名
    private Date createDate;//创建日期
    private double totalMoney;//总金额
    private double alreadyPay;//已经支付的钱数
    private String nextPayTime;//下次的支付时间
    private double discount;//回扣
    //0未发货   1发货了部分产品   2全部发货完成
    private int productState;
    //订单支付状态: 0未支付   1 支付部分   2支付完成
    private int payState;
    private String sendTime;//发货时间
    private String sendAddress;//地址
    private double sendPrice;//运费
    private String sendWay;//发货方式


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getAlreadyPay() {
        return alreadyPay;
    }

    public void setAlreadyPay(double alreadyPay) {
        this.alreadyPay = alreadyPay;
    }

    public String getNextPayTime() {
        return nextPayTime;
    }

    public void setNextPayTime(String nextPayTime) {
        this.nextPayTime = nextPayTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getProductState() {
        return productState;
    }

    public void setProductState(int productState) {
        this.productState = productState;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    public double getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(double sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getSendWay() {
        return sendWay;
    }

    public void setSendWay(String sendWay) {
        this.sendWay = sendWay;
    }

}
