package gg.abdiel.clip.simplerest;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		@SuppressWarnings("rawtypes")
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver");
		dataSourceBuilder.url("jdbc:hsqldb:mem:testdb;DB_CLOSE_DELAY=-1;hsqldb.write_delay=false;create=true;");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
}
