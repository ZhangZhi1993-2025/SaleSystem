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
	 * ���ó���
	 */
	@Override
	public void configConstant(Constants constants) {

		// ����ȫ�������ļ�
		// loadPropertyFile("properties.txt");

		constants.setDevMode(true/* getPropertyToBoolean("devMode") */);
		constants.setViewType(ViewType.JSP);
	}

	/**
	 * ���ô�����
	 */
	@Override
	public void configHandler(Handlers handlers) {

	}

	/**
	 * ����ȫ��������
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {

	}

	/**
	 * ���ò��
	 */
	@Override
	public void configPlugin(Plugins plugins) {

		// ����C3p0���ݿ����ӳز��
		/*
		 * C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
		 * getProperty("username"), getProperty("password"));
		 */
		C3p0Plugin c3p0Plugin = new C3p0Plugin(
				"jdbc:mysql://localhost:3306/salesystem", "root", "931065");
		plugins.add(c3p0Plugin);

		// ����ActiveRecord���
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		plugins.add(arp);

		/* ������ģ�������ݿ��ϵģʽ��ӳ�� */
		arp.addMapping("t_user", User.class);
		arp.addMapping("t_order", Order.class);
		arp.addMapping("t_authority", Authority.class);
		arp.addMapping("t_shoppingcar", ShoppingCar.class);

	}

	/**
	 * ����·��
	 */
	@Override
	public void configRoute(Routes routes) {

		routes.add("/entrance", EntranceController.class);
		routes.add("/user", UserController.class);
		routes.add("/admin", AdminController.class);
	}

}
