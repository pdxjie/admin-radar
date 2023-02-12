package com.pdx.radar.service.impl;

import com.pdx.radar.pojo.Log;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdx.radar.mapper.LogMapper;
import com.pdx.radar.service.LogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
