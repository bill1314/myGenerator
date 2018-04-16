package com.udb.template;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.test.context.ContextConfiguration;



public class DBInfoHelper {
  
  
  public DBInfoHelper()
  {
  }
  
  @Resource
  private SqlSessionFactory sqlSessionFactory;
  
  
  
  public  Map<String,List<ColumnForm>> init()
  {
   
    Map<String,List<ColumnForm>>  tablesMap=new HashMap<String,List<ColumnForm>>();
    try {
      Connection conn = sqlSessionFactory.openSession().getConnection();
      DatabaseMetaData data = conn.getMetaData();
       //获取表的信息
      ResultSet   tableRS = data.getTables(null, null, "%", null);
      while (tableRS.next()) {
        List <ColumnForm> list=new ArrayList<ColumnForm>();
        String tableName=tableRS.getString(3);
        ResultSet rs= data.getColumns(null,null,tableName,"%");
        System.out.println(tableName);
        while (rs.next()) {
          list.add(new ColumnForm(rs.getString("COLUMN_NAME"),rs.getString("TYPE_NAME"),rs.getString("TYPE_NAME")));
          System.out.println("   "+rs.getString("COLUMN_NAME")+" "+rs.getString("TYPE_NAME"));
        }
        tablesMap.put(tableName, list);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return tablesMap;
    
  }
  
  
  
  
  
  

}
