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


                        <form id="searcheUser" action="/todo/list" method="get">
                            <input type="hidden" id="myPageNo2" name="pageNo">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">事件关键字</label>
                                        <input type="text" id="keyword" name="keyword" value="${keyword?if_exists}"  class="form-control">
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
                                    <th>事项编号</th>
                                    <th>创建时间</th>
                                    <th>应该完成时间</th>
                                    <th data-hide="phone,tablet">事件</th>
                                    <th data-hide="phone,tablet">事项状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as task>
                                <tr class="gradeX">
                                    <td>${task.id}</td>
                                    <td>${(task.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                    <td>${task.doTime?if_exists}</td>
                                    <#if task.workState==0>
                                        <td class="center">未完成</td>
                                    <#else>
                                        <td class="center">完成</td>
                                    </#if>
                                    <td class="center">
                                        <form action="/todo/detail" method="get" id="signupForm">
                                            <button class="btn btn-info " onclick="mySubimt1(${task.id});return false;"
                                                    type="button"><i class="fa fa-paste"></i> 编辑
                                            </button>
                                            <button class="btn btn-warning "
                                                    onclick="mySubimt2(${task.id});return false;" type="button"><i
                                                    class="fa fa-warning"></i> <span class="bold">删除</span></button>
                                        </form>
                                    </td>
                                </tr>
                                    </#list>
                                <#else>
                                </#if>
                                </tbody>
                                <tr height="22%">
                                    <td colspan="7" align="center" id="myPageTd">
                                        <!-- 查到为空的时候判断 -->
                                        <#if pageInfo.totalPages gt 0>
                                    <#import  "../ge/pageShow.ftl" as page>
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/todo/list", 15, "blue"/>
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

<form action="/todo/detail" method="get" id="signupForm1">
    <input type="hidden" id="detailId1" name="id">
</form>

<form action="/todo/delete" method="post" id="signupForm2">
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
        $("#searcheUser").submit();
    }
</script>



</body>
</html>
