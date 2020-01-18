

package com.sym.modules.sys.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sym.modules.sys.entity.SysLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统日志
 *
 * @author suyongming
 */
@Mapper
public interface SysLogDao extends BaseMapper<SysLogEntity> {
	
}
