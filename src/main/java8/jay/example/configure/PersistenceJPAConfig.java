package jay.example.configure;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setPackagesToScan("jay.example.bean");
	      em.setPersistenceUnitName("SpringBootPer");
	      em.setDataSource(dataSource());
	    //  em.setPackagesToScan(new String[] { "org.baeldung.persistence.model" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	      return em;
	   }
	 
	   @Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa?createDatabaseIfNotExist=true");
	      dataSource.setUsername( "root" );
	      dataSource.setPassword( "root" );
	      return dataSource;
	   }
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(
	     EntityManagerFactory emf){
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(emf);
	       return transactionManager;
	   }
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	       return new PersistenceExceptionTranslationPostProcessor();
	   }
	   
	  /* @Bean
	   public LocalEntityManagerFactoryBean entityManagerFactoryPer(){
	        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
	       factoryBean.setPersistenceUnitName("SpringBootPer");
	       return factoryBean;
	   }
	 */
	   Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");  
	       return properties;
	   }
}
