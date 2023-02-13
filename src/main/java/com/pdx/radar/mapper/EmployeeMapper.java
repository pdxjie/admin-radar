package com.pdx.radar.mapper;

import com.pdx.radar.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
