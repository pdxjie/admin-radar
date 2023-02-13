package com.pdx.radar.service.impl;

import com.pdx.radar.pojo.Employee;
import com.pdx.radar.mapper.EmployeeMapper;
import com.pdx.radar.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
