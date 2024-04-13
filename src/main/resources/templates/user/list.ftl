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


                        <form id="searcheUser" action="/user/list" method="get">
                            <input type="hidden" id="myPageNo2" name="pageNo">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">用户名</label>
                                        <input type="text" id="keyword" name="keyword" value="${keyword?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="status">手机号</label>
                                        <input type="text" id="phone" name="phone" value="${phone?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">角色</label>
                                        <select class="form-control" name="role" id="role">
                                            <option [#if role?? && role == 0]selected[/#if] value="0">普通用户</option>
                                            <option [#if role?? && role == 1]selected[/#if] value="1">管理员</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">用户状态</label>
                                        <select class="form-control" name="userState" id="userState">
                                            <option  [#if userState?? && userState == 0]selected[/#if]   value="0">正常</option>
                                            <option  [#if userState?? && userState == 1]selected[/#if]   value="1">限制登录</option>
                                            <option  [#if userState?? && userState == 2]selected[/#if]   value="2">已经删除</option>
                                        </select>
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
                            <table class="footable table table-stripped" data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>用户编号</th>
                                    <th>用户名</th>
                                    <th>年纪</th>
                                    <th data-hide="phone,tablet">手机号</th>
                                    <th data-hide="phone,tablet">用户状态</th>
                                    <th data-hide="phone,tablet">用户身份</th>
                                    <th data-hide="phone,tablet">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as user>
                                <tr class="gradeX">
                                    <td>${user.id}</td>
                                    <td>${user.name?if_exists}</td>
                                    <td>${user.age?if_exists}</td>
                                    <td>${user.phone?if_exists}</td>
                                    <#if user.userState==0>
                                        <td class="center">正常</td>
                                    <#elseif user.userState==1>
                                        <td class="center">限制登录</td>
                                    <#else>
                                        <td class="center">已经删除</td>
                                    </#if>
                                    <#if user.role==0>
                                        <td class="center">普通用户</td>
                                    <#else>
                                        <td class="center">系统管理员</td>
                                    </#if>
                                    <td class="center">
                                        <form action="/user/detail" method="get" id="signupForm">
                                            <button class="btn btn-info " onclick="mySubimt1(${user.id});return false;"
                                                    type="button"><i class="fa fa-paste"></i> 编辑
                                            </button>
                                            <button class="btn btn-warning "
                                                    onclick="mySubimt2(${user.id});return false;" type="button"><i
                                                    class="fa fa-warning"></i> <span class="bold">删除</span></button>
                                            <button class="btn btn-warning "
                                                    onclick="mySubimt3(${user.id});return false;" type="button"><i
                                                    class="fa fa-warning"></i> <span class="bold">重置密码</span></button>
                                        </form>
                                    </td>
                                </tr>
                                    </#list>
                                <#else>
                                </#if>
                                </tbody>
                                <tr height="22%">
                                    <td colspan="7" align="center" id="myPageTd">
                                    <#if pageInfo.totalPages gt 0>
                                    <#import  "../ge/pageShow.ftl" as page>
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/user/list", 15, "blue"/>
                                        <br/>
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



<form action="/user/detail" method="get" id="signupForm1">
    <input type="hidden" id="detailId1" name="id">
</form>

<form action="/user/delete" method="post" id="signupForm2">
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

    function mySubimt3(id) {
        var rest = confirm("确认重置密码?")
        if (rest) {
            $.ajax({
                url: '/user/reset',
                dataType: 'json',
                method: 'POST',
                data: {"id": id},
                success: function (data) {
                    alert("重置密码成功");
                }
            });
        } else {
            return false
        }
    }

    function mySubimtSearch(pageNo) {
        //赋值
        $("#myPageNo2").val(pageNo)
        $("#searcheUser").submit();
    }

</script>


</body>
</html>
