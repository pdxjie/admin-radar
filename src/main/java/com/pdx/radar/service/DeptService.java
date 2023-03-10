package com.pdx.radar.service;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
public interface DeptService extends IService<Dept> {

    DataResult deptTree();

    DataResult deptInfo(Integer id);

    DataResult insertDept(Dept dept);

    DataResult modifyDept(Dept dept);

    DataResult deleteById(Integer id);
}
