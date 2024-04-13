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
                    <p>现在的图像是</p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                               <#if (user.img)??>
                                   <img alt="图像" class="border-radius: 130%" src="${user.img}"/>
                               <#else>
                                    <img alt="图像" class="border-radius: 130%" src="/static/img/profile_small.jpg"/>
                               </#if>
                            </div>
                        </div>
                    </div>
                    <p>请上传您的新图像(最大5M)</p>
                <form class="m-t" role="form" action="/img/upload" method="POST" id="signupForm" enctype="multipart/form-data">
                    <input name="id" type="hidden" value="${user.id}">
                    <input name="flag" type="hidden" value="1">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="form-group">
                                <div class="custom-file">
                                    <input id="logo" type="file" name="file" class="custom-file-input">
                                    <label for="logo" class="custom-file-label">选择图片</label>
                                </div>
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
        var form = new FormData(document.getElementById("signupForm"));
        //改为ajax提交密码....
        $.ajax({
            type : 'post',
            url: "/img/upload",
            data: form,
            processData: false,
            contentType: false,
            success : function(result) {
                alert(result);
            },
            error: function(e) {
                alert("图片上传是吧,请稍后再试")
            }
        });


    }
</script>

</html>
