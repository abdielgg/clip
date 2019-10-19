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
		dataSourceBuilder.url(
				"jdbc:hsqldb:file:~/simpleRestDB;" +
				"lock_file=false;" +
				"shutdown=true;" +
				"hsqldb.write_delay=false;" +
				"create=true;");
		dataSourceBuilder.username("SA");
		dataSourceBuilder.password("");
		return dataSourceBuilder.build();
	}
}
