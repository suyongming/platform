

package com.sym.modules.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sym.modules.job.entity.ScheduleJobLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务日志
 *
 * @author suyongming
 */
@Mapper
public interface ScheduleJobLogDao extends BaseMapper<ScheduleJobLogEntity> {
	
}
