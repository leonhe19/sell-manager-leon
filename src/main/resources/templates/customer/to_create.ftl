<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
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

        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>创建新客户</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 创建新客户</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/customer/create" method="post" id="signupForm">
                                        <fieldset>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">客户名:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="name" onchange="findName()"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">公司地址:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="companyAddress"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">联系人:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="person"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">联系人手机:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="phone"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">行业:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="profession"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">省-市:<span style="color: red">*</span></label>
                                                <div data-toggle="distpicker">
                                                    <select class="form-control" name="province"></select>
                                                    <select class="form-control" name="city"></select>
                                                </div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">竞争者:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="competitor"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">客户备注:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="note"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-银行名:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="bankName"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-银行账号:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="bankNo"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-发票地址:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="billAddress"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-发票接收人:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="billPerson"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-发票接收号码:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="billPhone"></div>
                                            </div>
                                            <div class="form-group row"><label
                                                    class="col-sm-2 col-form-label">开票-备注:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="billNote"></div>
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

    var flag=false

    function mySubimt() {
        var name = $('input[name="name"]').val().trim();
        var person = $('input[name="person"]').val().trim();
        var profession = $('input[name="profession"]').val().trim();
        if ("" == name) {
            alert("请输入公司名字!");
            $('input[name="name"]').focus();
            return;
        }
        if ("" == profession) {
            alert("请输入公司行业!");
            $('input[name="profession"]').focus();
            return;
        }
        if ("" == person) {
            alert("请输入公司联系人!");
            $('input[name="person"]').focus();
            return;
        }

        if (flag) {
            $('#signupForm').submit();
        } else {
            alert("请更改客户名");
            return
        }
    }



    function findName() {
        var name = $('input[name="name"]').val().trim();
        $.ajax({
            url: '/customer/name_search',
            dataType: 'json',
            method: 'POST',
            data: {"name": name},
            success:function(data) {
                if(0==data){
                    flag=true;
                    alert("此用户名可以使用");
                }else{
                    alert("此用户名已经被使用了,请换一个");
                }
            }
        });
    }
</script>

</body>

</html>

