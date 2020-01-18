

package com.sym.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sym.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author suyongming
 */
@Mapper
public interface SysCaptchaDao extends BaseMapper<SysCaptchaEntity> {

}
