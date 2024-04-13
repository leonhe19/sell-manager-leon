<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<script src="/static/js/api/laydate/laydate.js"></script>

<body>

<div id="page-wrapper" class="gray-bg">


    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>查看订单开票详情</h2>
        </div>
        <div class="col-lg-2">
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 查看订单开票详情</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <fieldset>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">客户名:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="subject" value="${(customer.name)!''}">
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">订单创建人:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="content" value="${user.name?if_exists}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-sm-2 col-form-label">货物名字:</label>
                                        <div class="col-sm-10">
                                            <table class="gridtable" id="productTable">
                                                <tr>
                                                    <th>产品名</th>
                                                    <th>产品编码</th>
                                                    <th>数量</th>
                                                    <th>单价</th>
                                                    <th>单位</th>
                                                    <th>总价</th>
                                                </tr>
                                                    <#if orderProducts ?? && (orderProducts?size > 0)>
                                                        <#list orderProducts as product>
                                                        <tr>
                                                            <td>${product.productName?if_exists}</td>
                                                            <td>${product.productCode?if_exists}</td>
                                                            <td>${product.amount?if_exists}</td>
                                                            <td>${product.univalent?if_exists} 元</td>
                                                            <td>${product.productUnit?if_exists}</td>
                                                            <td>${product.totalPrice?if_exists} 元</td>
                                                        </tr>
                                                        </#list>
                                                    </#if>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">订单创建时间:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="nextContent" value="${(order.createDate?string("yyyy-MM-dd"))!''}">
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">总金额:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"  value="${(order.totalMoney)!''}">
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">发货时间:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"  value="${(order.sendTime)!''}">
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">支付时间:</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control"  value="${(order.nextPayTime)!''}">
                                        </div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-银行名:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="bankName" value="${customer.bankName?if_exists}"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-银行账号:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="bankNo" value="${customer.branNo?if_exists}"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-发票地址:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="billAddress" value="${customer.billAddress?if_exists}"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-发票接收人:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="billPerson" value="${customer.billPerson?if_exists}"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-发票接收号码:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="billPhone" value="${customer.billPhone?if_exists}"></div>
                                    </div>
                                    <div class="form-group row"><label
                                            class="col-sm-2 col-form-label">开票-备注:</label>
                                        <div class="col-sm-10"><input type="text" class="form-control"  name="billNote" value="${customer.billNote?if_exists}"></div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        $('.input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
    });


</script>

<script>
    lay('#version').html('-v'+ laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#mylaydate' //指定元素
    });
</script>

<style type="text/css">
    table.gridtable {
        font-family: verdana,arial,sans-serif;
        font-size:11px;
        color:#333333;
        border-width: 1px;
        border-color: #666666;
        border-collapse: collapse;
    }
    table.gridtable th {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #dedede;
    }
    table.gridtable td {
        border-width: 1px;
        padding: 8px;
        border-style: solid;
        border-color: #666666;
        background-color: #ffffff;
    }
</style>

</body>

</html>

