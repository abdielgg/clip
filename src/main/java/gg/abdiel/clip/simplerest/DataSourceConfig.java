package gg.abdiel.clip.simplerest;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        @SuppressWarnings("rawtypes")
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.hsqldb.jdbc.JDBCDriver");
        dataSourceBuilder.url(  "jdbc:hsqldb:file:~/simpleRestDB;" +
                                "lock_file=false;" +
                                "shutdown=true;" +
                                "hsqldb.write_delay=false;" +
                                "create=true;");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        //em.setMappingResources("");
        factory.setPackagesToScan(  "gg.abdiel.clip.simplerest.entity",
                                    "gg.abdiel.clip.simplerest.repo");

        JpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(jpaAdapter);

        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
//        props.setProperty("hibernate.hbm2ddl.import_files", "insert-data.sql");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        factory.setJpaProperties(props);

        return factory;
    }
    
//    @Bean
//    public LocalSessionFactoryBean sessionFactory() {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(getDataSource());
//        return sessionFactory;
//    }
}
