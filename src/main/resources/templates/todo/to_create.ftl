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
            <h2>创建一个代办事项</h2>
        </div>
        <div class="col-lg-2">
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 创建代办事项</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <form action="/todo/create" method="post" id="signupForm">
                                    <fieldset>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">代办事项:<span style="color: red">*</span></label>
                                            <div class="col-sm-10"><input type="text" class="form-control"
                                                                          name="content"></div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">实践时间:<span style="color: red">*</span></label>
                                            <div class="col-sm-10"><input type="text" class="form-control"
                                                                          name="doTime"></div>
                                        </div>
                                    </fieldset>
                                    <button onclick="mySubimt();return false;"
                                            class="btn btn-primary block full-width m-b">确定
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
        var content = $('input[name="content"]').val().trim();
        var doTime = $('input[name="doTime"]').val().trim();
        if ("" == content) {
            alert("请输入内容!");
            $('input[name="content"]').focus();
            return;
        }
        if ("" == doTime) {
            alert("请输入时间!");
            $('input[name="doTime"]').focus();
            return;
        }
        $('#signupForm').submit();
    }

</script>

</body>

</html>

