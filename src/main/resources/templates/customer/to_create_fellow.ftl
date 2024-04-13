<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<!-- 头信息 -->
<#include "../ge/head.ftl">

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
                <h2>创建客户跟进</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 创建客户跟进</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/customer/create_fellow" method="post" id="signupForm">
                                        <fieldset>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">客户选择:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input disabled="disabled" value="${customer.name}">
                                                    <input type="hidden" name="customerId" value="${customer.id}">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">跟进标题:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <select name="subject">
                                                        <option value="电话">电话</option>
                                                        <option value="上门拜访">上门拜访</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">跟进详情:<span style="color: red">*</span></label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="content">
                                                </div>
                                            </div>
                                            <div class="form-group row"><label class="col-sm-2 col-form-label">
                                                下次跟进时间:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="demo-input" placeholder="请选择日期" autocomplete="off"
                                                           id="mylaydate" name="nextTime">
                                                </div>
                                            </div>

                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">下次跟进内容:</label>
                                                <div class="col-sm-10">
                                                    <input type="text" class="form-control" name="nextContent">
                                                </div>
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
        var subject = $('input[name="subject"]').val().trim();
        var content = $('input[name="content"]').val().trim();
        if ("" == subject) {
            alert("请输入标题!");
            $('input[name="subject"]').focus();
            return;
        }
        if ("" == content) {
            alert("请输入跟进内容!");
            $('input[name="content"]').focus();
            return;
        }
        $('#signupForm').submit();
    }


</script>

<script>
    lay('#version').html('-v' + laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#mylaydate' //指定元素
    });
</script>

</body>

</html>

