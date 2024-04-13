<!DOCTYPE html>
<html lang="zh">

<!-- 头信息 -->
<#include "ge/head.ftl">

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <!-- 左侧的栏 -->
    <#include "ge/left.ftl">
    </nav>
    <div id="page-wrapper" class="gray-bg">
        <!-- 顶部的搜索 -->
        <#include "ge/top.ftl">

        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-success float-right">本月</span>
                            <h5>收入</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${alreadyPay}</h1>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-info float-right">本月</span>
                            <h5>订单金额</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${totalMoney}</h1>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-danger float-right">今日</span>
                            <h5>订单个数</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${todayCount}</h1>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-primary float-right">今日</span>
                            <h5>订单金额</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${dayMoney}</h1>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-success float-right">公司</span>
                            <h5>目标</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${companyTarget}</h1>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <span class="label label-success float-right">个人</span>
                            <h5>目标</h5>
                        </div>
                        <div class="ibox-content">
                            <h1 class="no-margins">${userTarget}</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="ibox ">
                                <div class="ibox-title">
                                    <h5>今日待收款</h5>
                                    <div class="ibox-tools">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                        <a class="close-link">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <ul class="todo-list m-t small-list">
                                        <#if receipt ?? && (receipt?size > 0)>
                                            <#list receipt as receiptItem>
                                            <li>
                                                <span>${receiptItem["name"]}</span>
                                                <span>${receiptItem["totalMoney"]}</span>
                                                <br/>
                                            </li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="ibox ">
                                <div class="ibox-title">
                                    <h5>今日待发货</h5>
                                    <div class="ibox-tools">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                        <a class="close-link">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <ul class="todo-list m-t small-list">
                                        <#if sipping ?? && (sipping?size > 0)>
                                            <#list sipping as sippingItem>
                                            <li>
                                                <span>${sippingItem?if_exists}</span>
                                                <br/>
                                            </li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="ibox ">
                                <div class="ibox-title">
                                    <h5>代办事项</h5>
                                    <div class="ibox-tools">
                                        <a class="collapse-link">
                                            <i class="fa fa-chevron-up"></i>
                                        </a>
                                        <a class="close-link">
                                            <i class="fa fa-times"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="ibox-content">
                                    <ul class="todo-list m-t small-list">
                                        <#if toDoLists ?? && (toDoLists?size > 0)>
                                            <#list toDoLists as toDoList>
                                            <li onclick="doneWork(${toDoList.id})">
                                                <input type="checkbox"/><span id="${toDoList.id}">${toDoList.content}</span>
                                                <small class="label label-primary"><i class="fa fa-clock-o"></i>${toDoList.doTime}</small>
                                                <br>
                                            </li>
                                            </#list>
                                        </#if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function doneWork(id) {
        var tmp="#"+id
        $(tmp).css("text-decoration","line-through");
        $.ajax({
            url: '/todo/done',
            dataType: 'json',
            method: 'POST',
            data: {"id":id},
            success: function (result) {
                //成功
            }
        });
    }

</script>

<script type="text/javascript">
    var a_idx = 0;
    jQuery(document).ready(function($) {
        $("body").click(function(e) {
            var a = new Array("❤加油❤","❤努力❤","❤张鹏❤","❤孙晓兰❤","❤张志远❤");
            var $i = $("<span></span>").text(a[a_idx]);
            a_idx = (a_idx + 1) % a.length;
            var x = e.pageX,
                    y = e.pageY;
            $i.css({
                "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
                "top": y - 20,
                "left": x,
                "position": "absolute",
                "font-weight": "bold",
                "color": "rgb("+~~(255*Math.random())+","+~~(255*Math.random())+","+~~(255*Math.random())+")"
            });
            $("body").append($i);
            $i.animate({
                        "top": y - 180,
                        "opacity": 0
                    },
                    1500,
                    function() {
                        $i.remove();
                    });
        });
    });
</script>



</body>
</html>
