package com.pdx.radar.service;

import com.pdx.radar.common.DataResult;
import com.pdx.radar.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 派大星
 * @since 2023-02-13
 */
public interface MenuService extends IService<Menu> {

    DataResult getMenusByUserId();

    List<Menu> getMenusByRole();
}
