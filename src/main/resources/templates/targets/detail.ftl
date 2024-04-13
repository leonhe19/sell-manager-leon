<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<script src="/static/js/plugins/datapicker/bootstrap-datepicker.js"></script>

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
                <h2>编辑业绩目标</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 编辑业绩目标</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/targets/save" method="post" id="signupForm">
                                        <fieldset>
                                            <input type="text" class="form-control" hidden="hidden"  name="id" value="${target.id?if_exists}">
                                            <div class="form-group">
                                                <label class="col-form-label" for="date_added">开始时间:<span style="color: red">*</span></label>
                                                <div class="input-group date">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="date_added" type="text"   name="startTime" value="${target.startTime?if_exists}" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-form-label" for="date_modified">结束时间:<span style="color: red">*</span></label>
                                                <div class="input-group date">
                                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="date_modified" type="text"  name="endTime" class="form-control" value="${target.endTime?if_exists}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">目标:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="target" value="${target.target?if_exists}"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">用户:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <select name="customerId">
                                                        <#list users as user>
                                                            <option value="${user['id']}">${user['name']}</option>
                                                        </#list>
                                                    </select>
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


    //校验手机号
    function isPoneAvailable($poneInput) {
        var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
        if (!myreg.test($poneInput.val())) {
            return false;
        } else {
            return true;
        }
    }
</script>

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function() {

        $('.footable').footable();

        $('#date_added').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });

        $('#date_modified').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });

    });

</script>

</body>

</html>

