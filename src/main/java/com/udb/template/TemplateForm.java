package com.udb.template;


/**
 * 模板信息
 * @author bill
 *
 */
public class TemplateForm {
  
  
  private String name;
  private String pathName;
  private String module;
  private String suffix;
  

  public TemplateForm(String name, String pathName, String module,String suffix) {
    super();
    this.name = name;
    this.pathName = pathName;
    this.module = module;
    this.suffix=suffix;
  }
  
  
  public String getSuffix() {
    return suffix;
  }
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getPathName() {
    return pathName;
  }
  public void setPathName(String pathName) {
    this.pathName = pathName;
  }
  public String getModule() {
    return module;
  }
  public void setModule(String module) {
    this.module = module;
  }
  
  

}
