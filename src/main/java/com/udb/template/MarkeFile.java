package com.udb.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.context.ContextConfiguration;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

@ContextConfiguration(locations = { "classpath:spring-mybatis.xml" })
public class MarkeFile {
  
  
  public static void main(String[] args) {
    
    
    try {
      
      Map<String,List<ColumnForm>>  tablesMap=   new DBInfoHelper().init();
      
      Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
      cfg.setDirectoryForTemplateLoading(new File("D:/work/java/udb/wishcard_service/wishcard/wishcard-admin/src/main/java/com/udb/template/ftl/"));
      cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
      Template temp = cfg.getTemplate("controller.ftl");
      for (String key : tablesMap.keySet()) {
        
        String upperStr= key.substring(0, 1).toUpperCase() + key.substring(1);  

        String fileName = upperStr+"Controller.java";
        File file = new File("D:/template/" + fileName);
        Map root = new HashMap();
        root.put("ClassName", upperStr);
        root.put("IClassName", "I"+upperStr);
        root.put("tableName", key);
       /* List data = new ArrayList();
        data.add("11");
        data.add("12");
        data.add("13");
        root.put("data", data);*/
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(root, bw);
        bw.flush();
        fw.close();
    }

 
  } catch (Exception e) {
      e.printStackTrace();
  }
    
    
    
  }

}
