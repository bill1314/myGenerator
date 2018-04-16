<%@ page contentType="text/html; charset=utf-8" language="java"
    errorPage=""%>
<%@ include file="/inc/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理端--新增</title>

<link href="/css/common.css?v=<%=RESOURCE_VERSION%>" rel="stylesheet"
    type="text/css" />
<link href="/css/ui.css?v=<%=RESOURCE_VERSION%>" rel="stylesheet"
    type="text/css" />
<link href="/css/subject.css?v=<%=RESOURCE_VERSION%>"
    rel="stylesheet" type="text/css" />
<script src="/js/jquery.js?v=<%=RESOURCE_VERSION%>"></script>
</head>

<body>
     <input type="hidden" name="id" id="id" value="0"></input>
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
                        <input type="text" id="${row.columnName}" name="${row.columnName}" class="ui-input" />
                    </div>
                </li>
                </#if>
                </#list>
            </ul>
        </div>
    </div>
    <script src="/js/ui/jquery.combo.js?v=<%=RESOURCE_VERSION%>"></script>
    <script type="text/javascript" src="/js/common.js?v=<%=RESOURCE_VERSION%>"></script>
    <script type="text/javascript"
        src="/js/module/${tableName}/${tableName}add.js"></script>

</body>

</html>

