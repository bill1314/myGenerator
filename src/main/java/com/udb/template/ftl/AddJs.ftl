(function($) {
    // 变量
    var api = frameElement.api;
    var oper = "add";// api.data.oper;
    var operationCallback = api.data.callback;

    $(document).ready(function() {
        initPopBtns();
        $('input[type=text]:not(:disabled)').eq(0).focus().select();
    });

    function initPopBtns() {

        var operName = [ "保存", "关闭" ];
        api.button({
            id : 'confirm',
            name : operName[0],
            focus : true,
            callback : function() {

                var data = getData();
                doPostData(data);
                return false;
            }
        }, {
            id : 'cancel',
            name : operName[1]
        });
    }
    
    //保存数据
    function doPostData(data) {
        var subjectData = data;
        alert(data.adminid);
        // 提交数据
        if (data) {
                $.ajax({
                type : 'post',
                url : '/${tableName}/addAjax',
                data :JSON.stringify(data),
                dataType : 'json',
                contentType:'application/json',
                timeout : 10000,
                success : function(json) {
                    if(json.result == 1){
                         parent.Public.tips({ content : json.msg});
                         operationCallback();
                    }else if(json.result == 0){
                         parent.Public.tips({type:1, content : json.msg});
                         return;
                    }
                    if(parseInt(json.result) == -2){
                         parent.Public.tips({type:1, content : '无操作权限!'});
                         return;
                    }
                }
            });
        }
    }
    
    function getData() {
        <#list data as row>  
       var ${row.columnName} = $.trim($('#${row.columnName}').val());
      </#list>

        var data = {
            <#list data as row>
                 <#if row.columnName=="id">
                     id:0,
                  <#else>  
                    <#if row.columnNameLower!="addtime">
                      ${row.columnNameLower}:${row.columnName}<#if row_has_next>,</#if> //${row.memo}
                    </#if>
                  </#if>
            </#list>
        }
        return data;
    }

})(jQuery);
