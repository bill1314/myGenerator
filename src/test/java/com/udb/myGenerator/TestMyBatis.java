package com.udb.myGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.udb.template.ColumnForm;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class TestMyBatis {
  @Resource
  private SqlSessionFactory sqlSessionFactory;
  
  
  @Ignore
  public void tableInfo() {
    try {
      Connection conn = sqlSessionFactory.openSession().getConnection();
      DatabaseMetaData data = conn.getMetaData();
    //获取表的结果集
      ResultSet     tableRS = data.getTables(null, null, "%", null);
      while (tableRS.next()) {
        String tableName=tableRS.getString(3);
        ResultSet rs= data.getColumns(null,null,tableName,"%");
        System.out.println(tableName);
        while (rs.next()) {
      
          System.out.println("   "+rs.getString("COLUMN_NAME")+" "+rs.getString("TYPE_NAME")+ " "+rs.getString("REMARKS"));
          
        }
        
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  

  @Test
  public void markFile()
  {
 try {
      
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
         list.add(new ColumnForm(rs.getString("COLUMN_NAME"),rs.getString("TYPE_NAME"),rs.getString("REMARKS")));
         System.out.println("   "+rs.getString("COLUMN_NAME")+" "+rs.getString("TYPE_NAME")+" "+rs.getString("REMARKS"));
       }
       tablesMap.put(tableName, list);
     }
   } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
   }
       
     String templatePath="D:/work/java/udb/MyGenerator/myGenerator/src/main/java/com/udb/template/ftl";
     String savePath=""; 
     File templateDire=  new File(templatePath);
   
      Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
      cfg.setDirectoryForTemplateLoading(templateDire);
      cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
     
      
      File[] tempFiles= templateDire.listFiles();//获取所有的模板
      
      
      
      //创建controller
      Template controllerTemp = cfg.getTemplate("Controller.ftl");
      for (String key : tablesMap.keySet()) {
        key=key.substring(2);
        String upperStr= key.substring(0, 1).toUpperCase() + key.substring(1);  
        String fileName = upperStr+"Controller.java";
        String dpath="D:/template/controller/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("ClassName", upperStr);
        root.put("IClassName", "I"+upperStr);
        root.put("tableName", key);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        controllerTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      //创建Iservice
      Template IServiceTemp = cfg.getTemplate("IService.ftl");
      for (String key : tablesMap.keySet()) {
        key=key.substring(2);
        String upperStr= key.substring(0, 1).toUpperCase() + key.substring(1);  
        String fileName = "I"+upperStr+"Service.java";
        String dpath="D:/template/IService/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("ClassName", upperStr);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        IServiceTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      
      //创建ServiceImpl
      Template ServiceImplTemp = cfg.getTemplate("ServiceImpl.ftl");
      for (String key : tablesMap.keySet()) {
        key=key.substring(2);
        String upperStr= key.substring(0, 1).toUpperCase() + key.substring(1);  
        String fileName = upperStr+"ServiceImpl.java";
        String dpath="D:/template/ServiceImpl/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("ClassName", upperStr);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        ServiceImplTemp.process(root, bw);
        bw.flush();
        fw.close();
      }

 
  } catch (Exception e) {
      e.printStackTrace();
  }
  }
  
  
  
  @Test
  public void markWebFile()
  {
 try {
      
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
         list.add(new ColumnForm(rs.getString("COLUMN_NAME"),rs.getString("TYPE_NAME"),rs.getString("REMARKS")));
         System.out.println("   "+rs.getString("COLUMN_NAME")+" "+rs.getString("TYPE_NAME"));
       }
       tablesMap.put(tableName, list);
     }
   } catch (Exception e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
   }
      
      Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
      cfg.setDirectoryForTemplateLoading(new File("D:/work/java/udb/MyGenerator/myGenerator/src/main/java/com/udb/template/ftl"));
      cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
      //创建list jsp
      Template listjspTemp = cfg.getTemplate("ListJsp.ftl");
      for (String key : tablesMap.keySet()) {
        key=key.substring(2);
        String upperStr= key.substring(0, 1).toUpperCase() + key.substring(1);  
        String fileName = key+"List.jsp";
        String dpath="D:/template/jsp/"+key+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", key);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        listjspTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      //创建add jsp
      Template addjspTemp = cfg.getTemplate("AddJsp.ftl");
      for (String key : tablesMap.keySet()) {
        String tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = tableName+"Add.jsp";
        String dpath="D:/template/jsp/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        addjspTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      
      //创建modifyjsp 
      Template modifyjspTemp = cfg.getTemplate("ModifyJsp.ftl");
      for (String key : tablesMap.keySet()) {
        String tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = tableName+"Modify.jsp";
        String dpath="D:/template/jsp/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        modifyjspTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      
      
      //创建list js
      Template ListJsTemp = cfg.getTemplate("ListJs.ftl");
      for (String key : tablesMap.keySet()) {
        String   tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = tableName+"grid.js";
        String dpath="D:/template/js/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        ListJsTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      
      //创建add js
      Template addJsTemp = cfg.getTemplate("AddJs.ftl");
      for (String key : tablesMap.keySet()) {
       String tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = tableName+"add.js";
        String dpath="D:/template/js/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        addJsTemp.process(root, bw);
        bw.flush();
        fw.close();
      }
      
      //创建modfiy js
      Template modifyJsTemp = cfg.getTemplate("ModifyJs.ftl");
      for (String key : tablesMap.keySet()) {
       String tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = tableName+"modify.js";
        String dpath="D:/template/js/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        modifyJsTemp.process(root, bw);
        bw.flush();
        fw.close();
      }

 
  } catch (Exception e) {
      e.printStackTrace();
  }
  }
}
