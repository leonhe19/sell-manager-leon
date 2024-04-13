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
                <h2>创建业绩目标</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 创建业绩目标</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/targets/create" method="post" id="signupForm">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">开始时间:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text"  name="startTime"  autocomplete="off" class="demo-input" placeholder="请选择日期" id="mylaydate">
                                                </div>
                                            </div>
                                            <div class="form-group  row">
                                                <label  class="col-sm-2 col-form-label">结束时间:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text"  name="endTime"  autocomplete="off" class="demo-input" placeholder="请选择日期" id="mylaydate2">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">目标:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="target"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">用户:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <select name="userId">
                                                        <#list users as map>
                                                             <option value="${map['id']}">${map['name']}</option>
                                                        </#list>
                                                    </select>
                                                </div>
                                            </div>
                                        </fieldset>
                                        <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">创建</button>
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
        var startTime = $('input[name="startTime"]').val().trim();
        var endTime = $('input[name="endTime"]').val().trim();
        var target = $('input[name="target"]').val().trim();
        if ("" == startTime) {
            alert("请输入开始时间!");
            $('input[name="startTime"]').focus();
            return;
        }
        if ("" == endTime) {
            alert("请输入结束时间!");
            $('input[name="endTime"]').focus();
            return;
        }
        if ("" == target) {
            alert("请输入目标!");
            $('input[name="target"]').focus();
            return;
        }
        $('#signupForm').submit();
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

</body>

</html>

