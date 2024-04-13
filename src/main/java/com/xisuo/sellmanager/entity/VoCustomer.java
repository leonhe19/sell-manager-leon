package com.xisuo.sellmanager.entity;

import java.util.Date;

public class VoCustomer {


    private long id;
    private String name;
    private String companyAddress;
    private String person;
    private String phone;
    private String profession;//行业
    private String province;//省份
    private String city;//城市
    private Date createTime;//创建时间
    private long userId;
    private String userName;
    /**
     * 客户状态
     * 0:新建完成
     * 1:跟进中
     * 2:即将成交
     * 3:成交过的
     */
    private int customerState;
    private String competitor;//竞争对手
    private String note;
    private String bankName;
    private String bankNo;
    private String billAddress;
    private String billPerson;
    private String billPhone;
    private String billNote;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCustomerState() {
        return customerState;
    }

    public void setCustomerState(int customerState) {
        this.customerState = customerState;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getBillPerson() {
        return billPerson;
    }

    public void setBillPerson(String billPerson) {
        this.billPerson = billPerson;
    }

    public String getBillPhone() {
        return billPhone;
    }

    public void setBillPhone(String billPhone) {
        this.billPhone = billPhone;
    }

    public String getBillNote() {
        return billNote;
    }

    public void setBillNote(String billNote) {
        this.billNote = billNote;
    }
}
