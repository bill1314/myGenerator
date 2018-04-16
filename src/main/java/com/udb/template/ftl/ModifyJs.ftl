(function($) {
    // 变量
    var api = frameElement.api;
    var oper = "edit";// api.data.oper;
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

    //保存
    function doPostData(data) {
        var subjectData = data;
        // 提交数据
        if (data) {
            $.ajax({
                type : 'post',
                url : '/${tableName}/modifyAjax',
                contentType:'application/json',
                data : JSON.stringify(data),
                dataType : 'json',
                timeout : 10000,
                success : function(json) {
                    if(json.result == 1){
                        parent.Public.tips({ content : json.msg});
                        operationCallback();
                        return;
                    }else if(json.result == 0){
                         parent.Public.tips({type:1, content : json.msg});
                         return;
                    } else if(json.result==-2)
                     {
                        parent.Public.tips({type:1, content : '无操作权限!'});
                     }
                    
                },
                error : function(json) {
                    parent.Public.tips({type:1, content : '系统繁忙，请重试！'});
                }
            });
        }
    }

    //获取数据
    function getData() {

      <#list data as row>  
       var ${row.columnName} = $.trim($('#${row.columnName}').val());
      </#list>

        var data = {
            <#list data as row>  
                <#if row.columnNameLower!="addtime">
               ${row.columnNameLower}:${row.columnName}<#if row_has_next>,</#if> //${row.memo}
               </#if>
            </#list>
        }
        return data;
    }

})(jQuery);