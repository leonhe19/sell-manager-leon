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
                <h2>创建用户</h2>
            </div>
            <div class="col-lg-2">
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight ecommerce">

            <div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li><a class="nav-link active" data-toggle="tab" href="#tab-1"> 用户详情</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <form action="/user/create" method="post" id="signupForm">
                                        <input name="id" type="hidden" value="${user.id}">
                                    <fieldset>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">用户名:<span style="color: red">*</span></label>
                                            <div class="col-sm-10"><input type="text" class="form-control"   onchange="findName()"   name="name"></div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">年龄:</label>
                                            <div class="col-sm-10"><input type="text" class="form-control"  name="age"
                                                                            oninput="value=value.replace(/[^\d]/g,'')"></div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">手机号:<span style="color: red">*</span></label>
                                            <div class="col-sm-10"><input type="text" class="form-control"  name="phone"
                                                                           oninput="value=value.replace(/[^\d]/g,'')"></div>
                                        </div>
                                        <div class="form-group row"><label class="col-sm-2 col-form-label">
                                            状态:</label>
                                            <div class="col-sm-10">
                                                <select class="form-control"  name="userState">
                                                    <option  th:selected="selected"  value="0">正常</option>
                                                    <option  value="1">限制登录</option>
                                                    <option  value="2">已经删除</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group row"><label
                                                class="col-sm-2 col-form-label">用户角色:<span style="color: red">*</span></label>
                                            <div class="col-sm-10">
                                                <select class="form-control" name="role">
                                                    <option  th:selected="selected"  value="0">普通用户</option>
                                                    <option  value="1">管理员</option>
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

    var flag=false

    function mySubimt() {
        var age = $('input[name="age"]').val().trim();
        var name = $('input[name="name"]').val().trim();
        var phone = $('input[name="phone"]').val().trim();
        if ("" == age) {
            alert("请输入年纪!");
            $('input[name="age"]').focus();
            return;
        }
        if(name.length <3){
            alert("用户名最短是3个!");
            $('input[name="name"]').focus();
            return;
        }
        if(age<=0 || age>120){
            alert("输入正确的年纪,在1-120之间的整数!");
            $('input[name="age"]').focus();
            return;
        }
        if ("" == phone) {
            alert("请输入手机号!");
            $('input[name="phone"]').focus();
            return;
        }
        if(!isPoneAvailable(phone)){
            alert("请输入正确的手机号码!");
            $('input[name="phone"]').focus();
            return;
        }
        if (flag) {
            $('#signupForm').submit();
        } else {
            alert("请更改用户名");
            return
        }

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

    
    function findName() {
        var name = $('input[name="name"]').val().trim();
        $.ajax({
            url: '/user/name_search',
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

