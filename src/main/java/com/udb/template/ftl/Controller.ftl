package com.udb.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.udb.pojo.${ClassName};
import com.udb.service.${IClassName}Service;

/**
 * 控制类
 * @author bill
 *
 */
@Controller
@RequestMapping("/${tableName}")
public class ${ClassName}Controller {
  
  
  @Resource
  private ${IClassName}Service ${tableName}Service;//服务层接口
  

  /**
   * 列表访问
   * @return
   */
  @RequestMapping("/list")
  public  ModelAndView list()
  {
    return new ModelAndView("${tableName}/${tableName}List");    
  }
  
  /**
   * 查询列表记录
   * @param T 查询对象
   * @param page 页码
   * @param rows 每页条数
   * @return
   */
  @RequestMapping("/listAjax")
  public @ResponseBody Map listAjax( ${ClassName} ${tableName},  @RequestParam(required = false, defaultValue = "1") int page,
      @RequestParam(required = false, defaultValue = "20") int rows)
  {
    
    List<${ClassName}>  dataList= ${tableName}Service.selectByPage(${tableName}, page, rows);
    PageInfo pageInfo=new PageInfo<${ClassName}>(dataList);
    Map pageMap = new HashMap();
    pageMap.put("page", page); // 当前页数设置
    pageMap.put("total", pageInfo.getPages()); // 总页数
    pageMap.put("records", pageInfo.getTotal()); // 总记录数
    pageMap.put("rows",dataList);
    return pageMap;   
  }
  
   /**
   *访问添加
   * @return
   */
  @RequestMapping("/add")
  public  String add()
  {
    return "${tableName}/${tableName}Add";    
  }
  
   /***
   * 添加保存
   * @param address
   * @return
   */
  @RequestMapping("/addAjax")
  public @ResponseBody Map addAjax(@RequestBody ${ClassName} ${tableName})
  {
    Map resultMap = new HashMap();
    if(${tableName}!=null)
    {

      int rows=${tableName}Service.save(${tableName});
      if(rows>0)
      {
        resultMap.put("result",1);
        resultMap.put("msg","添加成功");
        return resultMap;
      }else
      {
        resultMap.put("result",0);
        resultMap.put("msg","添加失败");
      }
    }
    resultMap.put("result",1);
    return resultMap;    
  }
  

   /**
    * 访问修改
    * @param id
    * @return
    */
  @RequestMapping("/modify")
  public  ModelAndView modify(int id)
  {
    ${ClassName} ${tableName}=    ${tableName}Service.selectByKey(id);
    ModelAndView modelAndView=new ModelAndView();
    modelAndView.addObject("form", ${tableName});
    modelAndView.setViewName("${tableName}/${tableName}Modify");
    return modelAndView;    
  }
  
   /**
   * 修改保存
   * @param ${tableName} 保存对象
   * @return
   */
  @RequestMapping("/modifyAjax")
  public @ResponseBody Map modifyAjax(@RequestBody ${ClassName} ${tableName})
  {
    Map resultMap = new HashMap();
    if(${tableName}!=null)
    {
      int rows=${tableName}Service.updateNotNull(${tableName});
      if(rows>0)
      {
        resultMap.put("result",1);
        resultMap.put("msg","修改成功");
        return resultMap;
      }else
      {
        resultMap.put("result",0);
        resultMap.put("msg","修改失败");
      }
    }
    resultMap.put("result",1);
    return resultMap;    
  }
  
   /**
   * 删除
   * @param id
   * @return
   */
  @RequestMapping("/deleteAjax")
  public @ResponseBody Map deleteAjax(int id)
  {
    Map resultMap = new HashMap();
    int rows=${tableName}Service.delete(id);
    if(rows>0)
    {
      resultMap.put("result",1);
      resultMap.put("msg","添加成功");
      return resultMap;
    }else
    {
      resultMap.put("result",0);
      resultMap.put("msg","添加失败");
    }
    return resultMap;    
  }
 
}
