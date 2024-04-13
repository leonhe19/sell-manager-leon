<body>
<div class="sidebar-collapse">
    <ul class="nav metismenu" id="side-menu">
        <li class="nav-header">
            <div class="dropdown profile-element">
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                    <span  class="block m-t-xs font-bold">用户名:${user.name}</span>
                    <span  class="block m-t-xs font-bold"><a href="/user/detail?id=${user.id}">编辑资料</a></span>
                    <span  class="block m-t-xs font-bold"><a href="/user/reset_password">修改密码</a></span>
                </a>
            </div>
            <div class="logo-element">
                IN+
            </div>
        </li>
        <li>
            <a href="/index"><i class="fa fa-th-large"></i> <span class="nav-label">首页</span></a>
        </li>
        <li>
            <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">用户管理</span><span
                    class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/user/to_create">创建用户</a></li>
                <li><a href="/user/list">用户列表</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">客户管理</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/customer/to_create">创建客户</a></li>
                <li><a href="/customer/list">客户列表</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">产品管理</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/product/to_create">创建产品</a></li>
                <li><a href="/product/list">产品列表</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-files-o"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/order/to_create">创建订单</a></li>
                <li><a href="/order/list">订单列表</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-globe"></i> <span class="nav-label">目标管理</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/targets/to_create">创建目标</a></li>
                <li><a href="/targets/list">目标列表</a></li>
            </ul>
        </li>
        <li>
            <a href="#"><i class="fa fa-envelope"></i> <span class="nav-label">代办事项</span><span class="fa arrow"></span></a>
            <ul class="nav nav-second-level collapse">
                <li><a href="/todo/to_create">创建事项</a></li>
                <li><a href="/todo/list">事项列表</a></li>
            </ul>
        </li>
    </ul>
</div>

</body>
