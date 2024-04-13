<!DOCTYPE html>
<html lang="zh">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<body>

<div id="wrapper">

    <nav class="navbar-default navbar-static-side" role="navigation">
        <!-- 左侧的栏 -->
        <#include "../ge/left.ftl">
    </nav>

    <div id="page-wrapper" class="gray-bg">
        <!-- 顶部的搜索 -->
        <#include "../ge/top.ftl">

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>订单详情</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 订单详情</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/order/save" method="post" id="signupForm">
                                        <input type="hidden" value="${order.id?if_exists}">
                                        <fieldset>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">客户名:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  value="${order.customerName?if_exists}" disabled="disabled"></div>
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
                                                    class="col-sm-2 col-form-label">创建时间:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  value="${(order.createDate?string("yyyy-MM-dd HH:mm:ss"))!}"  disabled="disabled"></div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                总金额:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="totalMoney" value="${order.totalMoney?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                已经支付的金额:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="alreadyPay" value="${order.alreadyPay?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                下次支付时间:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="nextPayTime" value="${(order.nextPayTime?string("yyyy-MM-dd HH:mm:ss"))!}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                回扣:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="discount" value="${order.discount?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                产品状态:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="productState" value="${order.productState?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                付款状态:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="payState" value="${order.payState?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                发货时间:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="sendTime" value="${order.sendTime?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                发货地址:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="sendAddress" value="${order.sendAddress?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                运费:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="sendPrice" value="${order.sendPrice?if_exists}"></div>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                发货方式:</label>
                                                <div class="col-sm-10">
                                                    <div class="col-sm-10"><input type="text" class="form-control"  name="sendWay" value="${order.sendWay?if_exists}"></div>
                                                </div>
                                            </div>

                                        </fieldset>
                                        <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">返回列表
                                        </button>
                                    </form>
                                </div>
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
        $('.summernote').summernote();
        $('.input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
    });


    function mySubimt() {
        $('#signupForm').submit();
    }


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

