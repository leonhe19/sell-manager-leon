<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
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
                <h2>创建产品</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 新增产品</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/product/create" method="post" id="signupForm">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="col-sm-2 col-form-label">产品名:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control" name="name"  onchange="findName()"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">产品编码:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="code"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">产品单位:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="productUnit"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">供应商:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="supplier"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">备注:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="note"></div>
                                                <input type="hidden" name="productImg" id="productImg">
                                            </div>
                                        </fieldset>
                                        <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">提交
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
        var code = $('input[name="code"]').val().trim();
        if ("" == name) {
            alert("请输入名字!");
            $('input[name="name"]').focus();
            return;
        }
        if ("" == code) {
            alert("请输入产品编码!");
            $('input[name="code"]').focus();
            return;
        }
        if (flag) {
            $('#signupForm').submit();
        } else {
            alert("请更改产品名");
            return
        }
    }


    function findName() {
        var name = $('input[name="name"]').val().trim();
        $.ajax({
            url: '/product/name_search',
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

