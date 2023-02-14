package com.pdx.radar.controller;


import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.Dept;
import com.pdx.radar.service.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Api(tags = "部门接口管理")
@RestController
@RequestMapping("/radar/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "企业树形列表")
    @GetMapping("/tree")
    public DataResult deptTree(){
        return deptService.deptTree();
    }

    @ApiOperation(value = "根据企业id获取企业信息")
    @GetMapping("/info/{id}")
    public DataResult deptInfo(@PathVariable("id")Integer id){
        return deptService.deptInfo(id);
    }

    @ApiOperation(value = "添加企业信息")
    @PostMapping("/add")
    public DataResult insertDept(@RequestBody Dept dept){
        return deptService.insertDept(dept);
    }

    @ApiOperation(value = "修改企业信息")
    @PostMapping("/modify")
    public DataResult modifyDept(@RequestBody Dept dept){
        return deptService.modifyDept(dept);
    }

    @ApiOperation(value = "根据id删除企业信息")
    @DeleteMapping("/delete/{id}")
    public DataResult deleteById(@PathVariable("id")Integer id){
        return deptService.deleteById(id);
    }
}

