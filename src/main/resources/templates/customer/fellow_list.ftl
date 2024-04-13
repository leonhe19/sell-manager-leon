<!DOCTYPE html>
<html lang="zh">
<!-- 头信息 -->
<#include "../ge/head.ftl">

<!-- 日期插件 -->
<script src="/static/js/api/laydate/laydate.js"></script>

<script src="/static/js/api/layer/layer.js"></script>

<body>
<div id="wrapper">
    <nav class="navbar-default navbar-static-side" role="navigation">
        <!-- 左侧的栏 -->
        <#include "../ge/left.ftl">
    </nav>
    <div id="page-wrapper" class="gray-bg">
        <!-- 顶部的搜索 -->
        <#include "../ge/top.ftl">

        <div class="wrapper wrapper-content animated fadeInRight">

            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5>客户跟进列表</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>


                        <form id="searchFellowList" action="/customer/list" method="get">
                            <input type="hidden" id="myPageNo2" name="pageNo">
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="order_id">客户名</label>
                                        <input type="text" id="keyword" name="name" value="${name?if_exists}"
                                               class="form-control">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="status">主题</label>
                                        <select name="subject">
                                            <#if subject??>
                                                <#if subject == '电话'>
                                                <option value="电话">电话</option>
                                                <option value="上门拜访">上门拜访</option>
                                                <#else>
                                                <option value="上门拜访">上门拜访</option>
                                                <option value="电话">电话</option>
                                                </#if>
                                            <#else>
                                                <option>请选择</option>
                                                <option value="电话">电话</option>
                                                <option value="上门拜访">上门拜访</option>
                                            </#if>
                                        </select>
                                    </div>
                                </div>
                            </div>
                             <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">开始时间</label>
                                        <input type="text" class="demo-input" placeholder="请选择日期" autocomplete="off"
                                               id="mylaydate" name="startTime" value="${startTime?if_exists}">
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group">
                                        <label class="col-form-label" for="customer">结束时间</label>
                                        <input type="text" class="demo-input" placeholder="请选择日期" autocomplete="off"
                                               id="mylaydate" name="endTime" value="${endTime?if_exists}">
                                    </div>
                                </div>
                                 <div class="col-sm-4">
                                     <div class="form-group">
                                         <button class="btn btn-primary " type="button"   onclick="mySubimtSearch(1);return false;"><i class="fa fa-check"></i>&nbsp;搜索
                                         </button>
                                         </button>
                                     </div>
                                 </div>
                            </div>
                        </form>


                        <div class="ibox-content">
                            <a href="/customer/to_create_fellow?customerId=${customerId}">创建一个跟进</a>
                            <table class="footable table table-stripped"  data-filter=#filter>
                                <thead>
                                <tr>
                                    <th>跟进编号</th>
                                    <th>客户名</th>
                                    <th>跟进时间</th>
                                    <th data-hide="phone,tablet">主题</th>
                                    <th data-hide="phone,tablet">跟进内容</th>
                                    <th data-hide="phone,tablet">下次跟进时间</th>
                                    <th data-hide="phone,tablet">下次跟进内容</th>
                                    <th data-hide="phone,tablet">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#if pageInfo.data ?? && (pageInfo.data?size > 0)>
                                    <#list pageInfo.data as fellow>
                                <tr  class="gradeX">
                                    <td>${fellow.id?if_exists}</td>
                                    <td>${fellow.customerName?if_exists}</td>
                                    <td>${(fellow.createTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
                                    <td>${fellow.subject?if_exists}</td>
                                    <td>${fellow.content?if_exists}</td>
                                    <td>${fellow.nextTime?date}</td>
                                    <td>${fellow.nextContent?if_exists}</td>
                                    <td class="center">
                                            <button class="btn btn-info "     onclick="mySubimt1(${fellow.id});return false;"   type="button"><i class="fa fa-paste"></i> 查看详情</button>
                                            <button class="btn btn-warning "  onclick="mySubimt2(${fellow.id});return false;"  type="button"><i class="fa fa-warning"></i> <span class="bold">删除</span></button>
                                    </td>
                                </tr>
                                    </#list>
                                <#else>
                                </#if>
                                </tbody>
                                <tr height="22%">
                                    <td colspan="8" align="center">
                                    <#if pageInfo.totalPages gt 0>
                                    <#import  "../ge/pageShow.ftl" as page>
                                    <@page.pageShow pageInfo.totalPages, pageInfo.pageNo, "/customer/fellow_list", 15, "blue"/><br/>
                                    </#if>
                                    </td>

                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<form action="/customer/fellow_delete" method="post" id="signupForm2">
    <input type="hidden" id="detailId2" name="id">
    <input type="hidden"  name="customerId" value="${customerId}">
</form>


<script type="text/javascript">

    function mySubimt1(id) {
        var url="/customer/fellow_detail?id="+id
        layer.open({
            type: 2,
            title: '查看跟进详情',
            maxmin: true,
            area: ['720px', '550px'],
            content: url,
            end: function(){
                layer.tips('', '', '')
            }
        });

    }

    function mySubimt2(id) {
        var del = confirm("确认删除?")
        if (del) {
            $("#detailId2").val(id)
            $("#signupForm2").submit();
        } else {
            return false
        }
    }
</script>

<!-- 日期插件 -->
<script>
    lay('#version').html('-v'+ laydate.v);

    //执行一个laydate实例
    laydate.render({
        elem: '#mylaydate' //指定元素
    });
</script>

</body>
</html>
