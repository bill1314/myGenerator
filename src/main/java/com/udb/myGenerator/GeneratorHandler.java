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
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.udb.template.ColumnForm;
import com.udb.template.TemplateForm;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 自动生成器
 * @author bill
 *
 */
public class GeneratorHandler {
  
  public GeneratorHandler() {
    //初始化信息
    templatePath= System.getProperty("user.dir")+"/src/main/java/com/udb/template/ftl/";
  }
  private final static ConcurrentHashMap<String, List<ColumnForm>> tablesMap =
      new ConcurrentHashMap<String, List<ColumnForm>>();
  private final static List<TemplateForm> templateList=new ArrayList();
  private  static String templatePath;

  /**
   * 准备模板信息
   */
  private GeneratorHandler loadTemplateInfo()
  {
    templateList.add(new TemplateForm("AddJs.ftl","Add","js",".js"));
    templateList.add(new TemplateForm("AddJsp.ftl","Add","jsp",".jsp"));
    templateList.add(new TemplateForm("Controller.ftl","Controller","controller",".java"));
    templateList.add(new TemplateForm("IService.ftl","Service","service",".java"));
    templateList.add(new TemplateForm("ListJs.ftl","List","js",".js"));
    templateList.add(new TemplateForm("ListJsp.ftl","List","jsp",".jsp"));
    templateList.add(new TemplateForm("ModifyJs.ftl","Modify","js",".js"));
    templateList.add(new TemplateForm("ModifyJsp.ftl","Modify","jsp",".jsp"));
    templateList.add(new TemplateForm("ServiceImpl.ftl","ServiceImpl","serviceimpl",".java"));
    return this;
  }

  /**
   * 准备数据库表信息
   */
  private GeneratorHandler readTableInfo()
  {
      try {
        FileSystemXmlApplicationContext context=new FileSystemXmlApplicationContext(System.getProperty("user.dir")+"/src/main/resources/spring-mybatis.xml"); 
        SqlSessionFactory  sqlSessionFactory = (SqlSessionFactory) context.getBean("sqlSessionFactory");  
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
      return this;
  }
  
  
  private void markFile() throws Exception
  {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    cfg.setDirectoryForTemplateLoading(new File(templatePath));
    cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
    
    for(TemplateForm template:templateList)
    {
      Template addjspTemp = cfg.getTemplate(template.getName());
      for (String key : tablesMap.keySet()) {
        String tableName=key.substring(2);
        String upperStr= tableName.substring(0, 1).toUpperCase() + tableName.substring(1);  
        String fileName = upperStr+template.getPathName()+template.getSuffix();
        String dpath="D:/template/"+template.getModule()+"/"+tableName+"/";
        File file = new File(dpath + fileName);
        File f = new File(dpath);
        if (!f.exists()) {
          f.mkdirs();
        }
        Map root = new HashMap();
        root.put("tableName", tableName);
        root.put("ClassName", upperStr);
        root.put("IClassName", "I"+upperStr);
        List<ColumnForm>  colList= tablesMap.get(key);
        root.put("data", colList);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        addjspTemp.process(root, bw);
        bw.flush();
        fw.close();
    }
    
    }

  }
  
  
  //生成测试
  public static void main(String[] args) {
   GeneratorHandler generator=new GeneratorHandler();
    try {
      generator.loadTemplateInfo().readTableInfo().markFile();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }



}
