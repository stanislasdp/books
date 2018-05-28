package config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySources({
        @PropertySource("classpath:dbconnection.properties"),
        @PropertySource("classpath:hibernate.properties")
})
@ComponentScan(basePackages = { "dao.domain" })
public class DatabaseConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("dbUrl"));
        dataSource.setSchema(environment.getProperty("dbSchema"));
        dataSource.setUsername(environment.getProperty("dbuser"));
        dataSource.setPassword(environment.getProperty("dbpassword"));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("dao.domain");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("createMode"));
        properties.setProperty("hibernate.dialect", environment.getProperty("dialect"));
        properties.setProperty("show-sql", environment.getProperty("showSql"));
        properties.setProperty("hibernate.hbm2ddl.import_files", environment.getProperty("import"));
        properties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", environment.getProperty("extractor"));
        return properties;
    }

}