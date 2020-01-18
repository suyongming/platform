

package com.sym.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sym.modules.app.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 *
 * @author suyongming
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
