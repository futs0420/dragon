package com.dragon.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		String curName = DynamicDataSourceHolder.getDataSourceName();
		System.out.println("当前数据库连接：" +curName);
		return curName;
	}

}
