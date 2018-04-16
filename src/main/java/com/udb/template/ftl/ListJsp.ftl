<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ include file="/inc/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理端--列表</title>
<link type="text/css" rel="stylesheet" href="/css/jqgrid.css?v=<%=RESOURCE_VERSION %>" />
<link type="text/css" rel="stylesheet" href="/css/ui.css?v=<%=RESOURCE_VERSION %>" />
<link type="text/css" rel="stylesheet" href="/css/common.css?v=<%=RESOURCE_VERSION %>" />
<script src="/js/jquery.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/common.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/grid.locale-cn.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/grid.base.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/grid.custom.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/jquery.combo.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/jquery.dialog.js?self=true&v=<%=RESOURCE_VERSION %>"></script>
</head>
<body>
<div class="wrapper">
    <div class="mod-toolbar-top autoGrid" style="margin-bottom: 10px">
        <div class="left voucher-filter-bar"> 
            <div class="ui-btn-menu fl" id="filter-menu">
                <div >
                    <ul class="filter-list" id="filter-period">
                        <li>
                          <input placeholder="搜索" type="text" class="ui-input" id="key" name="key" />
                        </li>
                    </ul>
                </div>
            </div>
            <a id="filter-submit" class="ui-btn ui-btn-green fl" style="margin-top: 5px">查询</a>
        </div>
        <div style="float: right;margin-top: 5px" > <a id="add" class="ui-btn ui-btn-green fl">新增</a>
        </div>
    </div>
    <div id="dataGrid" class="autoGrid">
        <table id="grid">
        </table>
        <div id="page"></div>
    </div>
</div>
<script src="/js/module/${tableName}/${tableName}grid.js"></script>
</body>
</html>
