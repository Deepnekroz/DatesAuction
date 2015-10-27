package tk.softwareal.datesauction.config;


import tk.softwareal.datesauction.model.dao.user.UserDAO;
import tk.softwareal.datesauction.model.dao.user.impl.UserDAOImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by dmitry-sergeev on 03.09.15.
 */

@Configuration
@ComponentScan("tk.softwareal.datesauction")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
public class ApplicationContextConfig {
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dates_auction?useUnicode=yes&characterEncoding=UTF-8");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

        //sessionBuilder.addAnnotatedClasses(User.class, .class, ComponentType.class);
        sessionBuilder.scanPackages("com.sergeev.datesauction.**");
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.setProperty("hibernate.connection.CharSet", "utf8");
        sessionBuilder.setProperty("hibernate.connection.characterEncoding", "utf8");
        sessionBuilder.setProperty("hibernate.connection.useUnicode", "true");

        return sessionBuilder.buildSessionFactory();
    }
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);

        return transactionManager;
    }

    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
        return new UserDAOImpl(sessionFactory);
    }



    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }
}
