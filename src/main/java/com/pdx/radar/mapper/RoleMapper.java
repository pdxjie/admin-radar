package com.pdx.radar.mapper;

import com.pdx.radar.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesById(@Param("userId") int userId);
}
