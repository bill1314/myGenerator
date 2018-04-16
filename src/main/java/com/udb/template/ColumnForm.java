package com.udb.template;


/***
 * 表的列信息
 * @author bill
 *
 */
public class ColumnForm {
  
  private String columnName;
  private String columnType;
  private String memo;
  private String columnNameLower;
  
  public String getColumnNameLower() {
    return columnNameLower;
  }

  public void setColumnNameLower(String columnNameLower) {
    this.columnNameLower = columnNameLower;
  }

  public ColumnForm(String columnName, String columnType,String memo) {
    super();
    this.columnName = columnName;
    this.columnType = columnType;
    this.memo=memo;
    this.columnNameLower=columnName.toLowerCase();
  }
  
  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }
  public String getColumnType() {
    return columnType;
  }
  public void setColumnType(String columnType) {
    this.columnType = columnType;
  }
  public String getMemo() {
    return memo;
  }
  public void setMemo(String memo) {
    this.memo = memo;
  }
  

}
