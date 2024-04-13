<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<script src="/static/js/api/laydate/laydate.js"></script>

<body>

<div id="page-wrapper" class="gray-bg">


    <div class="row wrapper border-bottom white-bg page-heading">
        <div class="col-lg-10">
            <h2>查看客户跟进详情</h2>
        </div>
        <div class="col-lg-2">
        </div>
    </div>

    <div class="wrapper wrapper-content animated fadeInRight ecommerce">

        <div class="row">
            <div class="col-lg-12">
                <div class="tabs-container">
                    <ul class="nav nav-tabs">
                        <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 查看客户跟进详情</a></li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="tab-pane active">
                            <div class="panel-body">
                                <form action="/customer/fellow_list" method="GET" id="signupForm">
                                    <input type="hidden" name="customerId" value="${customer.id}">
                                </form>
                                    <fieldset>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">客户名:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="subject" value="${customer.name}">
                                            </div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">跟进标题:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="subject" value="${customerFollow.subject}">
                                            </div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">跟进详情:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="content" value="${customerFollow.content}">
                                            </div>
                                        </div>
                                        <div class="form-group row"><label class="col-sm-2 col-form-label">
                                            下次跟进时间:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="demo-input" placeholder="请选择日期" value="${customerFollow.nextTime}"
                                                       id="mylaydate" name="nextTime">
                                            </div>
                                        </div>

                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">下次跟进内容:</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="nextContent" value="${customerFollow.nextContent}">
                                            </div>
                                        </div>
                                    </fieldset>
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


</script>

<script>
    lay('#version').html('-v'+ laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#mylaydate' //指定元素
    });
</script>

</body>

</html>

