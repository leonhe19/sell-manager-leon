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

        <div class="wrapper wrapper-content">
            <div class="ibox-content">
                <form class="m-t" role="form" action="/user/modify_password" method="post" id="signupForm">
                    <input name="id" type="hidden" value="${user.id}">
                    <h2 class="font-bold">重置密码</h2>
                    <p>请出入您现在的密码<span style="color: red">*</span></p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <input type="password" name="oldPassword" class="form-control" placeholder="旧密码"
                                       required="">
                            </div>
                        </div>
                    </div>
                    <p>请出入您的新密码<span style="color: red">*</span></p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <input type="password" name="newPassword" class="form-control" placeholder="新密码"
                                       required="">
                            </div>
                            <button onclick="mySubimt();return false;" class="btn btn-primary block full-width m-b">确定
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</body>

<script type="application/javascript">
    function mySubimt() {
        var oldPassword = $('input[name="oldPassword"]').val().trim();
        var newPassword = $('input[name="newPassword"]').val().trim();
        if ("" == oldPassword) {
            alert("请输入旧密码!");
            $('input[name="oldPassword"]').focus();
            return;
        }
        if ("" == newPassword) {
            alert("请输入新密码!");
            $('input[name="newPassword"]').focus();
            return;
        }
        //改为ajax提交密码....
        $.ajax({
            type : 'post',
            url: "/user/modify_password",
            data: $('#signupForm').serialize(),
            success : function(result) {
                alert(result);
            }
        });


    }
</script>

</html>
