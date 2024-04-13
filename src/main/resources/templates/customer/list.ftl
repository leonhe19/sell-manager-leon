<!DOCTYPE html>
<html lang="zh">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<link href="/static/js/map/main.css" rel="stylesheet">
<script src="/static/js/map/distpicker.js"></script>
<script src="/static/js/map/main.js"></script>

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
                            <h5>客户列表</h5>
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


                        <form id="searchCustomer" action="/customer/list" method="get">
                            <input type="hidden" id="myPageNo2" name="pageNo">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">客户名</label>
                                        <input type="text" id="keyword" name="name" value="${name?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="status">联系人</label>
                                        <input type="text" id="phone" name="person" value="${person?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">行业</label>
                                        <input type="text" id="phone" name="person" value="${profession?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">创建者</label>
                                        <input type="text" id="phone" name="person" value="${userId?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">客户状态</label>
                                        <select class="form-control" name="customerState" id="customerState">
                                            <option  [#if customerState?? && customerState == 0]selected[/#if]   value="0">新建完成</option>
                                            <option  [#if customerState?? && customerState == 1]selected[/#if]   value="1">跟进中</option>
                                            <option  [#if customerState?? && customerState == 2]selected[/#if]   value="2">即将成交</option>
                                            <option  [#if customerState?? && customerState == 3]selected[/#if]   value="3">成交过的</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">省-市</label>
                                        <div data-toggle="distpicker">
                                            <select class="form-control" name="province"></select>
                                            <select class="form-control" name="city"></select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <button class="btn btn-primary " type="button"   onclick="mySubimtSearch(1);return false;"><i class="fa fa-check"></i>&nbsp;搜索
                                        </button>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>



                        <div class="ibox-content">
                            <table class="footable table table-stripped"  data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>客户名</th>
                                    <th>联系人</th>
                                    <th data-hide="phone,tablet">联系人手机号</th>
                                    <th data-hide="phone,tablet">行业</th>
                                    <th data-hide="phone,tablet">所在地</th>
                                    <th data-hide="phone,tablet">创建人名字</th>
                                    <th data-hide="phone,tablet">客户状态</th>
                                    <th data-hide="phone,tablet">竞争者</th>
                                    <th data-hide="phone,tablet">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as customer>
                                <tr  class="gradeX">
                                    <td>${customer.id}</td>
                                    <td>${customer.name?if_exists}</td>
                                    <td>${customer.person?if_exists}</td>
                                    <td>${customer.phone?if_exists}</td>
                                    <td>${customer.profession?if_exists}</td>
                                    <td>${customer.province?if_exists}-${customer.city?if_exists}</td>
                                    <td>${customer.userName?if_exists}</td>
                                    <td>
                                        <#if customer.customerState== 0>
                                        新建完成
                                        <#elseif customer.customerState== 1>
                                        跟进中
                                        <#elseif customer.customerState== 2>
                                        即将成交
                                        <#else>
                                        已经成交
                                        </#if>
                                    </td>
                                    <td>${customer.competitor?if_exists}</td>
                                    <td class="center">
                                        <form action="/user/detail" method="get" id="signupForm">
                                            <button class="btn btn-info "     onclick="mySubimt1(${customer.id});return false;"   type="button"><i class="fa fa-paste"></i> 编辑</button>
                                            <button class="btn btn-warning "  onclick="mySubimt2(${customer.id});return false;"  type="button"><i class="fa fa-warning"></i> <span class="bold">删除</span></button>
                                            <button class="btn btn-default " type="button"  onclick="window.location.href='/customer/to_create_fellow?customerId=${customer.id}'"><i class="fa fa-map-marker"></i>&nbsp;&nbsp;跟进客户</button>
                                            <button class="btn btn-default " type="button"  onclick="window.location.href='/customer/fellow_list?customerId=${customer.id}'"><i class="fa fa-map-marker"></i>&nbsp;&nbsp;跟进列表</button>
                                            <button class="btn btn-default " type="button"  onclick="window.location.href='/order/list?customerName=${customer.name}'"><i class="fa fa-map-marker"></i>&nbsp;&nbsp;所有订单</button>
                                        </form>
                                    </td>
                                </tr>
                                    </#list>
                                <#else>
                                </#if>
                                </tbody>
                                <tr height="22%">
                                    <td colspan="8" align="center">
                                    <#if pageInfo.totalPages gt 0>
                                    <#import  "../ge/pageShow.ftl" as page>
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/customer/list", 15, "blue"/><br/>
                                    </td>
                                    </#if>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<form action="/customer/detail" method="get" id="signupForm1">
    <input type="hidden" id="detailId1" name="id">
</form>

<form action="/customer/delete" method="post" id="signupForm2">
    <input type="hidden" id="detailId2" name="id">
</form>


<script type="text/javascript">

    function mySubimt1(id) {
        $("#detailId1").val(id)
        $("#signupForm1").submit();
    }

    function mySubimt2(id) {
        var del = confirm("确认删除?")
        if (del) {
            $("#detailId2").val(id)
            $("#signupForm2").submit();
        } else {
            return false
        }
    }

    function mySubimtSearch(pageNo) {
        //赋值
        $("#myPageNo2").val(pageNo)
        $("#searchCustomer").submit();
    }

</script>

</body>
</html>
