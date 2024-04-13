<!DOCTYPE html>
<html lang="zh">
<!-- 头信息 -->
<#include "../ge/head.ftl">
<!-- 日期插件 -->
<script src="/static/js/api/laydate/laydate.js"></script>
<script src="/static/js/api/layer/layer.js"></script>

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <!-- 左侧的栏 -->
        <#include "../ge/left.ftl">
    </nav>
    <div id="page-wrapper" class="gray-bg">
        <!-- 顶部的搜索 -->
        <#include "../ge/top.ftl">

        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>订单列表</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>


                        <form id="searchOrder" action="/customer/list" method="get">
                            <input type="hidden" id="myPageNo2" name="pageNo">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">客户名</label>
                                        <input type="text" id="keyword" name="customerName" value="${customerName?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="status">创建人</label>
                                        <input type="text" id="phone" name="person" value="${person?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">创建开始时间</label>
                                            <input type="text"  name="startTime"  class="demo-input" placeholder="请选择开始时间"  autocomplete="off"  id="mylaydate">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">创建结束时间</label>
                                        <input type="text"  name="endTime"  class="demo-input" placeholder="请选择结束时间"  autocomplete="off"  id="mylaydate2">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">发货时间</label>
                                        <input type="text"  name="sendTime"  class="demo-input" placeholder="请选择发货时间"  autocomplete="off"  id="mylaydate3">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <button class="btn btn-primary " type="button"   onclick="mySubimtSearch(1);return false;"><i class="fa fa-check"></i>&nbsp;搜索
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <div class="ibox-content">
                            <table class="footable table table-stripped"  data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>订单编号</th>
                                    <th>创建者</th>
                                    <th>客户名</th>
                                    <th data-hide="phone,tablet">创建时间</th>
                                    <th data-hide="phone,tablet">总金额</th>
                                    <th data-hide="phone,tablet">回扣</th>
                                    <th data-hide="phone,tablet">产品状态</th>
                                    <th data-hide="phone,tablet">付款状态</th>
                                    <th data-hide="phone,tablet">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as order>
                                <tr  class="gradeX">
                                    <td>${order.id?if_exists}</td>
                                    <td>${order.userName?if_exists}</td>
                                    <td>${order.customerName?if_exists}</td>
                                    <td>${(order.createDate?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                    <td>${order.totalMoney?if_exists}</td>
                                    <td>${order.discount?if_exists}</td>
                                    <td>
                                        <#if order.productState==0>
                                        未发货
                                        <#elseif order.productState==1>
                                        发货部分
                                        <#else>
                                        发货完成
                                        </#if>
                                    </td>
                                    <td>
                                        <#if order.payState==0>
                                        未支付
                                        <#elseif order.payState==1>
                                        支付部分
                                        <#else>
                                        支付完成
                                        </#if>
                                    </td>
                                    <td class="center">
                                        <form action="#" method="get" id="signupForm">
                                            <button class="btn btn-info "     onclick="mySubimt2(${order.id});return false;"  type="button"><i class="fa fa-warning"></i> <span class="bold">订单明细</span></button>
                                            <#--<button class="btn btn-info "     onclick="mySubimt1(${order.id});return false;"   type="button"><i class="fa fa-paste"></i> 编辑</button>-->
                                            <button class="btn btn-info "     onclick="mySubimt3(${order.id});return false;"   type="button"><i class="fa fa-paste"></i> 开票信息</button>
                                        </form>
                                    </td>
                                </tr>
                                    </#list>
                                <#else>
                                </#if>
                                </tbody>
                                <tr height="22%">
                                    <td colspan="7" align="center">
                                    <#if pageInfo.totalPages gt 0>
                                    <#import  "../ge/pageShow.ftl" as page>
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/order/list", 15, "blue"/><br/>
                                    </#if>
                                    </td>

                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<form action="/order/to_edit" method="GET" id="signupForm1">
    <input type="hidden" id="detailId1" name="id">
</form>

<form action="/order/detail" method="GET" id="signupForm2">
    <input type="hidden" id="detailId2" name="id">
</form>

<form action="/order/to_invoice" method="GET" id="signupForm3">
    <input type="hidden" id="detailId3" name="id">
</form>


<script type="text/javascript">

    function mySubimt1(id) {
        $("#detailId1").val(id)
        $("#signupForm1").submit();
    }

    function mySubimt2(id) {
        $("#detailId2").val(id)
        $("#signupForm2").submit();
    }

    function mySubimt3(id) {
        var url="/order/to_invoice?id="+id
        layer.open({
            type: 2,
            title: '查看开票详情',
            maxmin: true,
            area: ['720px', '550px'],
            content: url,
            end: function(){
                layer.tips('', '', '')
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
    laydate.render({
        elem: '#mylaydate3' //指定元素
    });
</script>

</body>
</html>
