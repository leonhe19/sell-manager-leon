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
                            <h5>业绩目标列表</h5>
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
                        <div class="ibox-content">
                            <input type="text" class="form-control form-control-sm m-b-xs" id="filter"
                                   placeholder="搜索">
                            <table class="footable table table-stripped"  data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>用户名</th>
                                    <th>开始时间</th>
                                    <th data-hide="phone,tablet">结束时间</th>
                                    <th data-hide="phone,tablet">目标</th>
                                    <th data-hide="phone,tablet">已经完成</th>
                                    <th data-hide="phone,tablet">是否完成</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as target>
                                <tr  class="gradeX">
                                    <td>${target.id?if_exists}</td>
                                    <td>${target.userName?if_exists}</td>
                                    <td>${target.startTime?if_exists}</td>
                                    <td>${target.endTime?if_exists}</td>
                                    <td>${target.target?if_exists}</td>
                                    <td>${target.hasDone?if_exists}</td>
                                    <#if target.targetState==0>
                                        <td class="center">未完成</td>
                                    <#else>
                                        <td class="center">已经完成</td>
                                    </#if>
                                    <td class="center">
                                        <form action="/targets/detail" method="get" id="signupForm">
                                            <button class="btn btn-info "     onclick="mySubimt1(${target.id});return false;"   type="button"><i class="fa fa-paste"></i> 编辑</button>
                                            <button class="btn btn-warning "  onclick="mySubimt2(${target.id});return false;"  type="button"><i class="fa fa-warning"></i> <span class="bold">删除</span></button>
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
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/targets/list", 15, "blue"/><br/>
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

<form action="/targets/detail" method="get" id="signupForm1">
    <input type="hidden" id="detailId1" name="id">
</form>

<form action="/targets/delete" method="post" id="signupForm2">
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
</script>

</body>
</html>
