<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li class="dropdown-header">订单管理</li>
        <li>
            <a href="/sell/seller/order/list?shopId=${shopIdCpy}"><i class="fa fa-fw fa-list-alt"></i> 订单列表</a>
        </li>
        <li class="dropdown open">
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">商品管理</li>
                <li><a href="/sell/seller/product/list?shopId=${shopIdCpy}">商品列表</a></li>
                <li><a href="/sell/seller/product/index?shopId=${shopIdCpy}">新增商品</a></li>
            </ul>
        </li>
<#--        <li class="dropdown open">-->
<#--            <ul class="dropdown-menu" role="menu">-->
<#--                <li class="dropdown-header">操作</li>-->
<#--                <li><a href="/sell/seller/category/list">列表</a></li>-->
<#--                <li><a href="/sell/seller/category/index">新增</a></li>-->
<#--            </ul>-->
<#--        </li>-->
        <li>
            <a href="/sell/seller/function/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>