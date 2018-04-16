<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<%@ include file="/inc/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理端--修改</title>
<link type="text/css" rel="stylesheet" href="/css/jqgrid.css?v=<%=RESOURCE_VERSION %>" />
<link type="text/css" rel="stylesheet" href="/css/ui.css?v=<%=RESOURCE_VERSION %>" />
<link type="text/css" rel="stylesheet" href="/css/common.css?v=<%=RESOURCE_VERSION %>" />
<link href="/css/jquery.ui.datepicker.css" rel="stylesheet" type="text/css"/>
<link href="/css/jquery.ui.core.css" rel="stylesheet" type="text/css"/>
<script src="/js/jquery.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/common.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/jquery.ui.datepicker.js"></script>
<script src="/js/ui/jquery.ui.datepicker-zh-CN.js" charset="utf8"></script>
<script src="/js/ui/jquery.ui.core.js"></script>
<script src="/js/ui/grid.locale-cn.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/grid.base.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/grid.custom.js?v=<%=RESOURCE_VERSION %>"></script>
<script src="/js/ui/jquery.combo.js?v=<%=RESOURCE_VERSION %>"></script>
<link href="/css/subject.css?v=<%=RESOURCE_VERSION %>" rel="stylesheet" type="text/css" />
</head>

<body>
    <input type="hidden" name="id" id="id" value="${r'${form.id}'}"></input>
    <div class="subject-wrap" id="subject-wrap">
        <div class="subject-add" id="subject-add">
            <ul class="mod-form-rows">
                <#list data as row>
                 <#if row.columnName!="id"&&row.columnNameLower!="addtime">
                    <li class="row-item">
                        <div class="label-wrap">
                            <label for="loginId">${row.memo}</label>
                        </div>
                        <div class="ctn-wrap code-wrap">
                            <input type="text" id="${row.columnName}" name="${row.columnName}"  class="ui-input" value="${r'${form.'}${row.columnNameLower}}" />
                        </div>
                    </li>
                </#if>
                </#list>
            </ul>
        </div>
    </div>

<script src="/js/ui/jquery.combo.js?v=<%=RESOURCE_VERSION %>"></script>
<script type="text/javascript" src="/js/common.js?v=<%=RESOURCE_VERSION %>"></script>
<script type="text/javascript" src="/js/module/${tableName}/${tableName}modify.js"></script>

</body>

</html>

