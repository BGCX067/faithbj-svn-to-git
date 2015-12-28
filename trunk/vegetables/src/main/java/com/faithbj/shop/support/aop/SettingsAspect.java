package com.faithbj.shop.support.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.faithbj.shop.web.controller.admin.BaseAdminController;
import com.faithbj.shop.web.controller.shop.BaseShopController;

@Aspect
@Component
public class SettingsAspect {

	/**
     * 为前台的每个controller里面的方法，添加settings配置，前提是每个controller都有ModelMap参数,且ModelMap是第一个参数
     * @param jp 连接点类本身
	 * @throws Exception  类型转换失败
     */
	@Before("@annotation(com.faithbj.shop.support.annotation.NeedNavigation)")
    public void addNavigationAndSystemSettings(JoinPoint jp) throws Exception {
		Object args=jp.getArgs()[0];//获得参数列表种的第一参数
		Object target=jp.getTarget();
		if(target instanceof BaseShopController){
			BaseShopController targetController=(BaseShopController)target;
			
			if(null!=args){
				ModelMap modelMap=(ModelMap)args;
				targetController.addNavigationList(modelMap);//添加导航信息，以后会缓存起来
				targetController.addSystemConfig(modelMap);//添加setting配置文件信息
				targetController.addFriendLinkList(modelMap);//添加友情链接
			}
		}
		else{
			throw new Exception("类型转换失败");
		}
    }
	
	/**
     * 为前台的每个controller里面的方法，添加settings配置，前提是每个controller都有ModelMap参数,且ModelMap是第一个参数
     * @param jp 连接点类本身
	 * @throws Exception  类型转换失败
     */
	@Before("@annotation(com.faithbj.shop.support.annotation.NeedSetting)")
    public void addSystemSettings(JoinPoint jp) throws Exception {
		Object args=jp.getArgs()[0];//获得参数列表种的第一参数
		Object target=jp.getTarget();
		if(target instanceof BaseAdminController){
			BaseAdminController targetController=(BaseAdminController)target;
			
			if(null!=args){
				ModelMap modelMap=(ModelMap)args;
				targetController.addSystemConfig(modelMap);//添加setting配置文件信息
			}
		}
		else{
			throw new Exception("类型转换失败");
		}
    }


}
