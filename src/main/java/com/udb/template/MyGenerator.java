package com.udb.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class MyGenerator {
  
  
  public static void main(String[] args) {
    try {
        Map root = new HashMap();
        root.put("str", "hello world!");
        List data = new ArrayList();
        data.add("11");
        data.add("12");
        data.add("13");
        root.put("data", data);
        
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File("D:/template/"));
        cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        Template temp = cfg.getTemplate("demo.ftl");
        String fileName = "demo.htm";
        File file = new File("D:/template/" + fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(root, bw);
        bw.flush();
        fw.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

}
  
  

}
