<!DOCTYPE html>
<html  lang="zh">

<!-- 头信息 -->
<#include "ge/head.ftl">

<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <h3>Welcome 来到上海兮索</h3>
            <p>一个专业的电气生产商品</p>
            <p>请登录</p>
            <div>
                <span id="basic-addon0">&nbsp;</span>
                <span style="font-size: 12px;color: red"  aria-describedby="basic-addon0">${msg?if_exists}</span>
                <br/>
            </div>
            <form class="m-t" role="form" method="post" action="/login" id="signupForm">
                <div class="form-group">
                    <input type="text" name="username"  class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input type="password" name="password"  class="form-control" placeholder="密码">
                </div>
                <button  onclick="mySubimt();return false;"  class="btn btn-primary block full-width m-b">登录</button>
                <a href="#"><small>忘记密码了,请联系管理员</small></a>
            </form>
            <p class="m-t"> <small>上海兮索ERP 版本V3.9 &copy; 2019</small> </p>
        </div>
    </div>
</body>

<script type="application/javascript">
    function mySubimt() {
        var username = $('input[name="username"]').val().trim();
        var password = $('input[name="password"]').val().trim();
        if ("" == username) {
            alert("请输入用户名!");
            $('input[name="username"]').focus();
            return;
        }
        if ("" == password) {
            alert("请输入密码!");
            $('input[name="username"]').focus();
            return;
        }
        $("#signupForm").submit();
    }
</script>
</html>

