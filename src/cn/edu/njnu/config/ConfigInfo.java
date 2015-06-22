package cn.edu.njnu.config;

import cn.edu.njnu.controller.*;
import cn.edu.njnu.model.*;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class ConfigInfo extends JFinalConfig {

	/**
	 * 配置常量
	 */
	@Override
	public void configConstant(Constants constants) {

		// 加载全局配置文件
		// loadPropertyFile("properties.txt");

		constants.setDevMode(true/* getPropertyToBoolean("devMode") */);
		constants.setViewType(ViewType.JSP);
	}

	/**
	 * 配置处理器
	 */
	@Override
	public void configHandler(Handlers handlers) {

	}

	/**
	 * 配置全局拦截器
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {

	}

	/**
	 * 配置插件
	 */
	@Override
	public void configPlugin(Plugins plugins) {

		// 配置C3p0数据库连接池插件
		/*
		 * C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
		 * getProperty("username"), getProperty("password"));
		 */
		C3p0Plugin c3p0Plugin = new C3p0Plugin(
				"jdbc:mysql://localhost:3306/salesystem", "root", "931065");
		plugins.add(c3p0Plugin);

		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		plugins.add(arp);

		/* 富领域模型与数据库关系模式的映射 */
		arp.addMapping("t_user", User.class);
		arp.addMapping("t_order", Order.class);
		arp.addMapping("t_authority", Authority.class);
		arp.addMapping("t_shoppingcar", ShoppingCar.class);

	}

	/**
	 * 配置路由
	 */
	@Override
	public void configRoute(Routes routes) {

		routes.add("/entrance", EntranceController.class);
		routes.add("/user", UserController.class);
		routes.add("/admin", AdminController.class);
	}

}
