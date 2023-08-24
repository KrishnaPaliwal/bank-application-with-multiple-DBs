package com.example.bank.account.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableAutoConfiguration

  @EnableJpaRepositories( basePackages = "com.example.bank.repository.account",
  entityManagerFactoryRef = "accountEntityManagerFactory",
  transactionManagerRef = "accountTransactionManager" )
 
public class AccountDataSourceConfig {


    @Bean(name = "accountDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.account")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "accountDataSource")
    public DataSource dataSource(@Qualifier("accountDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


    @Bean(name = "accountEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean accountEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("accountDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.bank.model")
                .persistenceUnit("account")
                .build();
    }


    @Bean(name = "accountTransactionManager")
    public PlatformTransactionManager accountTransactionManager(
            @Qualifier("accountEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
