package com.dragon.dao;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class MainDataSource {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		System.out.println("11111111111111");
		return new DynamicDataSource();
	}
	
}
