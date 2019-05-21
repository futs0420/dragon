package com.dragon.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.dragon.dao.DBRouterKey.RouteType;


public class DynamicDataSourceRouteHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 看该方法是否需要路由
		DBRouter[] annotation = method.getAnnotationsByType(DBRouter.class);
		if (annotation == null || annotation.length == 0) {
			return method.invoke(method, args);
		} else {
			boolean isFound = false;
			RouteType routeType = null;
			Object routeValue = null;
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				Annotation[] oneAnno = parameterAnnotations[i];
				for (Annotation one : oneAnno) {
					if (one instanceof DBRouterKey) {
						routeValue = args[i];
						routeType = ((DBRouterKey) one).routeType();
						isFound = true;
						break;
					}
				}
			}
			if (isFound) {
				if (routeType == RouteType.BATCHID) {
					// 计算这个ID应该走哪个数据库
					if (routeValue.equals("1")) {
						DynamicDataSourceHolder.putDataSourceName("dataSourceOne");
					} else {
						DynamicDataSourceHolder.putDataSourceName("dataSourceTwo");
					}
				}

			}
			return method.invoke(method, args);
		}
	}

}
