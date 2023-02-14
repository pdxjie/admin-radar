package com.pdx.radar.service.impl;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.Menu;
import com.pdx.radar.mapper.MenuMapper;
import com.pdx.radar.pojo.User;
import com.pdx.radar.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 根据用户id获取菜单
     * @return
     */
    @Override
    public DataResult getMenusByUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //从Redis中获取菜单数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menus_" + user.getId());
        //判断是否为空
        if (CollectionUtils.isEmpty(menus)){
            menus = baseMapper.getMenusByUserId(user.getId());
            valueOperations.set("menus_" + user.getId(),menus);
        }
        return DataResult.success(menus);
    }
}
