<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<!-- 头信息 -->
<#include "../ge/head.ftl">
<!-- 日期插件 -->
<script src="/static/js/api/laydate/laydate.js"></script>

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
                <h2>创建订单</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 创建订单</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/order/create" method="post" id="signupForm">
                                        <fieldset>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">客户:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <select name="customerId">
                                                        <#list customers as customer>
                                                            <option value="${customer['id']}">${customer['name']}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="form-group row">
                                                <label class="col-sm-2 col-form-label">货物名字:</label>
                                                <div class="col-sm-10">
                                                    <form method="GET" id="verify_name_form" action="/product/verify_name">
                                                    <table class="gridtable" id="productTable">
                                                        <tr>
                                                            <th>产品名</th>
                                                            <th>数量</th>
                                                            <th>单价</th>
                                                            <th><a onclick='addTr()'>增加一行</a></th>
                                                        </tr>
                                                        <tr id='1'>
                                                            <th>
                                                                <select name="productId">
                                                                    <#list products as product>
                                                                        <option value="${product['id']}">${product['name']}</option>
                                                                    </#list>
                                                                </select>
                                                            </th>
                                                            <th><input   name='amount'></th>
                                                            <th><input  name='prices'></th>
                                                            <th><a onclick='deleteTr(1)'>删除本行</a></th>
                                                        </tr>
                                                    </table>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                付款时间:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="demo-input"  autocomplete="off"  name="nextPayTime" placeholder="请选择日期" id="mylaydate">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                发货方式:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="sendWay">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                发货时间:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="demo-input"  autocomplete="off"  name="sendTime" placeholder="请选择日期" id="mylaydate2">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">发货地址:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="sendAddress">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">发货运费:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="sendPrice">
                                                </div>
                                            </div>
                                        </fieldset>
                                        <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">确定
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
        $('.input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
    });

    function mySubimt() {
        var sendTime = $('input[name="sendTime"]').val().trim();
        if ("" == sendTime) {
            alert("请输入发货时间!");
            $('input[name="sendTime"]').focus();
            return;
        }
        var nextPayTime = $('input[name="nextPayTime"]').val().trim();
        if ("" == nextPayTime) {
            alert("请输入付款时间!");
            $('input[name="nextPayTime"]').focus();
            return;
        }
        //不再需要校验产品名字了 verifyName();
        //提交表单
        $('#signupForm').submit();
    }


    //校验产品名
    function verifyName() {
        var names = $("input[name='productName']");
        $.ajax({
            url: '/product/verify_name',
            dataType: 'json',
            method: 'GET',
            data: names,
            success:function(res){
                if(res == 0 ){
                    //提交表单
                    $('#signupForm').submit();
                }else{
                    alert("产品名字错误的有:"+res);
                    return 0;
                }
            }
        });

    }

</script>

<!-- 日期插件 -->
<script>
    lay('#version').html('-v'+ laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#mylaydate' //指定元素
    });
    laydate.render({
        elem: '#mylaydate2' //指定元素
    });
</script>

<script type="text/javascript">
    var tmp=1;
    tmp= tmp+1;
    //增加一行
    function addTr() {
        var tr= "<tr id='"+tmp+"'>" +
                "        <th><select name='productId'>" +
                "        <#list products as product>"+
                "         <option value='${product['id']}'>${product['name']}</option>" +
                "         </#list>" +
                "         </select></th>" +
                "        <th><input   name='amount'></th>" +
                "        <th><input name='prices'></th>" +
                "        <th><a onclick='deleteTr("+tmp+")'>删除本行</a></th>" +
                "    </tr>";
        $("#productTable").append($(tr))
    }

    //删除一行
    function deleteTr(id) {
        var obj = $("#"+id)
        obj.remove();
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

