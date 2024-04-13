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
                <h2>产品编辑</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 产品编辑</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/product/save" method="post" id="signupForm">
                                        <input type="hidden" name="id" value="${product.id}">
                                        <fieldset>
                                            <div class="form-group row">
                                                <label class="col-sm-2 col-form-label">产品名:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control" name="name" value="${product.name?if_exists}"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">产品编码:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="code" value="${product.code?if_exists}"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">产品单位:<span style="color: red">*</span></label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="productUnit"  value="${product.productUnit?if_exists}"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">供应商:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="supplier" value="${product.supplier?if_exists}"></div>
                                            </div>
                                            <div class="form-group row">
                                                <label  class="col-sm-2 col-form-label">备注:</label>
                                                <div class="col-sm-10"><input type="text" class="form-control"  name="note" value="${product.note?if_exists}"></div>
                                            </div>
                                        </fieldset>
                                        <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">确定修改
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
        $('.summernote').summernote();
        $('.input-group.date').datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            autoclose: true
        });
    });


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
        $('#signupForm').submit();
    }
</script>

</body>

</html>

