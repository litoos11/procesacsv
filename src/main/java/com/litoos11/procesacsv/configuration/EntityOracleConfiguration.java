package com.litoos11.procesacsv.configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import oracle.jdbc.pool.OracleDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class EntityOracleConfiguration.
 */
@Configuration
@PropertySource(value= {"classpath:application.properties"})
public class EntityOracleConfiguration {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(EntityOracleConfiguration.class);

	/** The environment. */
	@Autowired
    Environment environment;
	
	/**
	 * Data source.
	 *
	 * @return the data source
	 * @throws SQLException the SQL exception
	 */
	@Bean
	public DataSource dataSource() throws SQLException {
		//LOG.info("--URL: " + environment.getProperty("spring.datasource.url"));
		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setDriverType(environment.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUser(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		dataSource.setURL(environment.getProperty("spring.datasource.url"));
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
	}	
	
	
	
	/**
	 * Hibernate properties.
	 *
	 * @return the properties
	 */
	@Bean
	public Properties hibernateProperties(){
	    final Properties properties = new Properties();
	    
	    properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.show_sql", "true");
		properties.put( "hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect" );
		properties.put( "hibernate.connection.driver_class", "oracle.jdbc.OracleDriver" );
		properties.put("hibernate.id.new_generator_mappings", true);
	    return properties;
	}
	
	
	/**
	 * Entity manager factory.
	 *
	 * @return the local container entity manager factory bean
	 */
	//@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
	    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	    try {
	    	em.setDataSource(this.dataSource());
		    em.setPackagesToScan( "com.litoos11.procesacsv.entity" );
		    em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
		    em.setJpaProperties(this.hibernateProperties());
		    //em.setPersistenceUnitName( "mytestdomain" );
		    em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		    em.afterPropertiesSet();	    	
	    }catch(Exception ex) {
	    	//ex.printStackTrace();
	    	LOG.error("ERROR METHOD entityManagerFactory() "+ ex.getMessage());
	    }    

	    return em;

	}


}