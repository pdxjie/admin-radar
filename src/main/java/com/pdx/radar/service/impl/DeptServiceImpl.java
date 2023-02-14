package com.pdx.radar.service.impl;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.Dept;
import com.pdx.radar.mapper.DeptMapper;
import com.pdx.radar.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    /**
     * 企业树形列表
     * @return
     */
    @Override
    public DataResult deptTree() {
        List<Dept> tree = baseMapper.deptTree();
        return DataResult.success(tree);
    }

    /**
     * 根据企业id获取信息
     * @param id
     * @return
     */
    @Override
    public DataResult deptInfo(Integer id) {
        Dept dept = baseMapper.selectById(id);
        return DataResult.success(dept);
    }

    @Override
    public DataResult insertDept(Dept dept) {
        int result = baseMapper.insert(dept);
        if (result != 1){
            return DataResult.getResult(500,"操作失败");
        }
        return DataResult.success();
    }

    @Override
    public DataResult modifyDept(Dept dept) {
        int result = baseMapper.updateById(dept);
        if (result != 1){
            return DataResult.getResult(500,"操作失败");
        }
        return DataResult.success();
    }

    @Override
    public DataResult deleteById(Integer id) {
        int result = baseMapper.deleteById(id);
        if (result != 1){
            return DataResult.getResult(500,"操作失败");
        }
        return DataResult.success();
    }
}
