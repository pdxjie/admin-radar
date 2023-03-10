package com.pdx.radar.mapper;

import com.pdx.radar.pojo.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> deptTree();
}
