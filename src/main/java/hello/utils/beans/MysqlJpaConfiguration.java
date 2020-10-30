package hello.utils.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("hello.repository")
@PropertySource("classpath:application.properties")
public class MysqlJpaConfiguration {

    private final String PROPERTY_DRIVER = "spring.datasource.driver";
    private final String PROPERTY_URL = "spring.datasource.url";
    private final String PROPERTY_USERNAME = "spring.datasource.username";
    private final String PROPERTY_PASSWORD = "spring.datasource.password";
    private final String PROPERTY_DIALECT = "spring.jpa.properties.hibernate.dialect";
    private final String PROPERTY_SHOW_SQL = "spring.jpa.show-sql";
    private final String PROPERTY_DDL_AUTO = "spring.jpa.hibernate.ddl-auto";

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.env.getProperty(PROPERTY_DRIVER));
        dataSource.setUrl(this.env.getProperty(PROPERTY_URL));
        dataSource.setUsername(this.env.getProperty(PROPERTY_USERNAME));
        dataSource.setPassword(this.env.getProperty(PROPERTY_PASSWORD));
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect",this.env.getProperty(PROPERTY_DIALECT));
        properties.setProperty("hibernate.show-sql",this.env.getProperty(PROPERTY_SHOW_SQL));
        properties.setProperty("hibernate.ddl-auto",this.env.getProperty(PROPERTY_DDL_AUTO));
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("hello.entity");
        lemfb.setJpaProperties(hibernateProps());
        return lemfb;
    }
}
