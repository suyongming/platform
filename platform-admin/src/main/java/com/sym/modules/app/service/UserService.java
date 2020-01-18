

package com.sym.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sym.modules.app.entity.UserEntity;
import com.sym.modules.app.form.LoginForm;

/**
 * 用户
 *
 * @author suyongming
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
