package jp.co.forthelocal.template.spring.test;

import javax.sql.DataSource;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.io.ClassPathResource;

class DBUtil {
	static DataSource dataSource() {
		Properties properties = DBUtil.properties();

		DataSource ds = DataSourceBuilder.create()
				.username(properties.getProperty("spring.datasource.username", "root"))
				.password(properties.getProperty("spring.datasource.password", ""))
				.url(properties.getProperty("spring.datasource.url"))
				.driverClassName(properties.getProperty("spring.datasource.driver-class-name", "com.mysql.jdbc.Driver"))
				.build();
		return ds;
	}

	static SqlSessionFactory sqlSessionFactory() throws Exception {

		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(DBUtil.dataSource());
		SqlSessionFactory sqlSessionFactory = factory.getObject();
		assert sqlSessionFactory != null;
		sqlSessionFactory.getConfiguration().addMappers(DBUtil.properties().getProperty("spring.mybatis.mapper-locations"));
		return sqlSessionFactory;
	}

	private static Properties properties() {
		ClassPathResource resource = new ClassPathResource("/config/application.yml");

		YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
		factoryBean.setResources(resource);
		return factoryBean.getObject();
	}

}
