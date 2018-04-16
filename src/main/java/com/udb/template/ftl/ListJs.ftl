$(function(){

    //新增
        $('#add').on('click',function(e){
            e.preventDefault();
            var rowId = $(this).parents('tr').attr('id');
            $.dialog({
            title : '添加数据',
            content : 'url:/${tableName}/add',
            data: {id:rowId, callback: operateCallback},
            width : 450,
            height : 450,
            max : false,
            min : false,
            cache : false,
            lock: true
           });

        });
         //修改
        $('#grid').on('click', '.edit', function(e){
            e.preventDefault();
            var rowId = $(this).parents('tr').attr('id');           
            $.dialog({
            title : '修改数据',
            content : 'url:/${tableName}/modify?id='+rowId,
            data: {id:rowId, callback: operateCallback},
            width : 450,
            height : 450,
            max : false,
            min : false,
            cache : false,
            lock: true
           });

        });
        //删除
        $('#grid').on('click', '.delete', function(e){
            e.preventDefault();
            var rowId = $(this).parents('tr').attr('id');
            deleteRow(rowId);
        });

         //批量删除
        $('#delete').on('click', function(e){
            e.preventDefault();
            var $_cbox = $('.cbox:checked').not('#cb_grid');
            if( $_cbox.length > 0) {
                var rowIdArr = [];
                $_cbox.each(function(index, element) {
                    rowIdArr.push((this.id).replace("jqg_grid_", ""));
                });
                deleteRows(rowIdArr);
            } else {
                Public.tips({type:2, content : '请选择你要删除的数据！'});
            }
        });


      //查询
      $("#filter-submit").click(function(){
        filterConditions = {key:$("#key").val()};
            $("#grid").jqGrid('setGridParam',{url:"", postData: filterConditions, datatype: "json"}).trigger("reloadGrid");

      });


      //刷新
     $("#refresh").click(function(e){
         e.preventDefault();
         $("#grid").jqGrid('setGridParam',{url:'', datatype: "json"}).trigger("reloadGrid");
      });

     initGrid();//初始化
});

  //添加/修改回调方法
  function operateCallback(){
        $("#grid").jqGrid('setGridParam',{url:'/${tableName}/listAjax', datatype: "json"}).trigger("reloadGrid");
    }


//初始化Grid
function initGrid(){
     var initWH = Public.setGrid();
     var widthArray = [130, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100];
     
     var colNames =[
       <#list data as row>
       <#if row.columnName=="id">
         '操作',
        <#else>
       '${row.memo}'<#if row_has_next>,</#if>
       </#if>
      </#list>
     ];
     var autoW = initWH.w -100-100-100-100-100-100-100;
        if (autoW > 350) {
            autoW = 350;
        } else if(autoW < 150){
            autoW = 150;
        }
        var colModel = [
                         <#list data as row>     
                          <#if row.columnName=="id">
                            {name:'id',index:'id',width:widthArray[0],title:false,align:"center", sortable:false,formatter:linkFmatter },
                          <#else>
                               {name:'${row.columnNameLower}',index:'${row.columnName}', width:widthArray[0],title:false,align:"center", sortable:false}<#if row_has_next>,</#if>
                          </#if>
                        </#list>
                        
                   ]   
        $('#grid').jqGrid({
            url:'/${tableName}/listAjax',
            datatype: 'json',
            height: initWH.h,
            autowidth: true,
            shrinkToFit:true,
            forceFit:false,
            altRows:true,
            gridview: true,
            colNames:colNames,
            colModel:colModel,
            page: 1, 
            viewsortcols: [true],
            pager: "#page",  
            rowNum: 30,  
            rowList:[10,20,30],
            viewrecords: true,
            scroll: 0
        });
        
    }
    
    
    
    function linkFmatter (val, opt, row) {
        var result = '<a class="edit" title="修改">修改</a><a class="delete" title="删除">删除</a>';
        return result;
    }
    

        //删除
    function deleteRow(rowId){
        $.dialog.confirm('您确定要删除数据吗？删除后不可恢复！ ', function(){
            $.ajax({
               type: 'post',
               dataType: 'json',
               url: "/${tableName}/deleteAjax?id=" + rowId,
               success: function(data){
                var data = eval("(" + data + ")");
                 if(data.result==1)
                 {
                      parent.Public.tips({content : data.msg});
                   $('#grid').jqGrid('delRowData',rowId);
                 }
                 else if(data.result==-1)
                 {
                    window.location.href="/Login.dx/login";
                 } 
                  else if(data.result==-2)
                 {
                    parent.Public.tips({type:1, content : '无操作权限!'});
                 }
               },
               error: function(){
                    Public.tips({type:1, content : '系统繁忙，请重试！'});
               }
            });
        });
    }


    //批量删除
    function deleteRows(rowIdArr){
        $ .dialog.confirm('您确定要删除这些数据吗？删除后不可恢复！', function(){
                $ .ajax({
                   type: "POST",
                     dataType: 'json',
                    url: "/AdminUserInfo.dx/deleteAjax?ids=" +rowIdArr.join(','),
                   success: function(data){
                             if(data.result==0)
                             {
                                deleteRowsCallback(rowIdArr);
                              }
                             else if(data.result==-1)
                             {
                                window.location.href="/Login.dx/login";
                             }  
                              else if(data.result==-2)
                             {
                              parent.Public.tips({type:1, content : '无操作权限!'});
                             }
                             else
                             {
                                 Public.tips({type:1, content : '删除失败！'});
                              }
                   },
                    error : function(){
                        Public.tips({type:1, content : '系统繁忙，请重试！'});
                    }
                });
            });
    }
    
    function deleteRowsCallback(rowIdArr){
        for(var i  =0, len = rowIdArr.length; i < len; i++)
         {
            var rowId = rowIdArr[i];
            $("#grid").jqGrid('delRowData',rowId);
            }
        };

 
