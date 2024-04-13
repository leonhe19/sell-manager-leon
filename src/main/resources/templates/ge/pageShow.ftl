<#-- 分页控件-->
<#-- 分页组件的使用 -->
<#--
pageShow 总页数, 当前页, 请求服务地址, 页面大小默认15, 显示最多分页个数, 请求服务地址参数默认为p, 分页样式（可选：gray（默认）、orange、blue）
<#import "pub/pageShow.ftl" as page>
<@page.pageShow pageCount, curPage, "pagePluginTest.do", pageSize, "blue"/>


pageShow 总页数, 当前页, 请求服务地址, 页面大小默认15, 显示最多分页个数, 请求服务地址参数默认为p, 分页样式（可选：gray（默认）、orange、blue）
使用:
<#import "pub/pageShow.ftl" as page>
<@page.pageShow pageCount, curPage, "pagePluginTest.do", pageSize, "gray"/><br/>
<@page.pageShow pageCount, curPage, "pagePluginTest.do", pageSize, "blue"/><br/>
<@page.pageShow pageCount, curPage, "pagePluginTest.do", pageSize, "orange"/>

_defaultPageParameter   传递到后台的 要取的页码的参数名

_maxShowNums 下方最多显示多少个页码的按钮
-->

<#macro pageShow _totalPageNum _currentPageNo _pageName _pageSize=15 _styleClassName="gray" _maxShowNums=10 _defaultPageParameter="pageNo" >
<#-- 本段默认起始页 -->
    <#local _pageStartNo = 1/>
<#-- 本段默认结束页 -->
    <#local _pageEndNo = _totalPageNum />
<#-- 分页的第一页 -->
    <#local _firstPageNo = 1 />
<#-- 分页的最后一页 -->
    <#local _lastPageNo = _totalPageNum>
<#-- 前一页号码 -->
    <#local _prePageNo = _currentPageNo-1 />
<#-- 后一页号码 -->
    <#local _nextPageNo = _currentPageNo+1 />

    <#if _currentPageNo == _pageStartNo>
        <#local _prePageNo = _pageStartNo />
    </#if>

    <#if _currentPageNo == _lastPageNo>
        <#local _nextPageNo = _lastPageNo />
    </#if>

    <#if _currentPageNo gt _totalPageNum>
        <#local _thisPageSegment = ((_currentPageNo-1) / _totalPageNum)?int + 1/>
        <#assign _thisPageStartNo = _pageStartNo + (_thisPageSegment-1) * _totalPageNum/>
        <#assign _thisPageEndNo = _pageEndNo + (_thisPageSegment-1) * _totalPageNum/>
        <#if _thisPageEndNo gt _totalPageNum>
            <#assign _thisPageEndNo = _totalPageNum>
        </#if>
    <#else>
        <#assign _thisPageStartNo = _pageStartNo />
        <#assign _thisPageEndNo = _pageEndNo />
    </#if>

<#-- 构造当前分页栏上面的分页按钮 -->
    <#local _pages=[] />
    <#if _totalPageNum != 0>
        <#list _thisPageStartNo .. _thisPageEndNo as _index>
            <#if _currentPageNo == _index >
                <#local _pages = _pages + [{"pageNum" : _index ,"url" : _pageName+"?"+_defaultPageParameter+"="+_index , "current" : true}] />
            <#else>
                <#local _pages = _pages + [{"pageNum" : _index ,"url" : _pageName+"?"+_defaultPageParameter+"="+_index , "current" : false}] />
            </#if>
        </#list>
    </#if>

<#-- 构造分页栏HTML代码 -->
    <#local _htmlNoLinkLine>
        <a  onclick="mySubimtSearch(${_firstPageNo})" target = "_self">首页</a>
        <#if _currentPageNo != _pageStartNo>
            <a  onclick="mySubimtSearch(${_prePageNo})"    target = "_self">上一页</a>
        </#if>

        <#list _pages as page>
            <#if page.current?default(false)>
                <span class="current" >${page.pageNum}</span>
            <#else>
                <a  target="_self"  onclick="mySubimtSearch(${page.pageNum})" >${page.pageNum}</a>
            </#if>
        </#list>

        <#if _currentPageNo != _lastPageNo>
            <a target = "_self"    onclick="mySubimtSearch(${_nextPageNo})">下一页</a>
        </#if>
        <a  target = "_self" onclick="mySubimtSearch(${_lastPageNo})">末页</a>
        <span>共${_totalPageNum?default(0)}页</span>
    </#local>


    <!-- 将分页HTML代码放置到页面 -->
    <div class="${_styleClassName}">
        ${_htmlNoLinkLine}
    </div>

    <!-- 分页插件CSS -->
    <style type="text/css" mce_bogus="1">
        <#if _styleClassName == "blue">
        <#-- CSS blue style pagination -->
        div.blue {
            font-size: 12px;
            font-family: verdana, arial, helvetica, sans-serif;
            padding: 3px;
            margin: 3px;
            text-align: center;
            color:#999999;
        }
        div.blue a {
            border: #E1E2E3 1px solid;
            padding: 7px 14px;
            margin: 2px;
            color: #808080;
            text-decoration: none;
        }
        div.blue a:hover {
            border: #389CFF 1px solid;
            background: transparent;
            color:#389CFF;
        }
        div.blue a:active {
            color: #389CFF;
        }
        div.blue span.current {
            border: #389CFF 1px solid;
            background: #389CFF;
            font-weight: bold;
            margin: 2px;
            padding: 7px 14px;
            color: #FFFFFF;
        }
        </#if>
    </style>
</#macro>