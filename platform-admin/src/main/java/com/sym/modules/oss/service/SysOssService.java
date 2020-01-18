

package com.sym.modules.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sym.common.utils.PageUtils;
import com.sym.modules.oss.entity.SysOssEntity;

import java.util.Map;

/**
 * 文件上传
 *
 * @author suyongming
 */
public interface SysOssService extends IService<SysOssEntity> {

	PageUtils queryPage(Map<String, Object> params);
}
