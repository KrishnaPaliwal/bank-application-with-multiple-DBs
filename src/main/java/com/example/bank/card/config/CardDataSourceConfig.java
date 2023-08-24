package com.example.bank.card.config;

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

  @EnableJpaRepositories( basePackages = "com.example.bank.repository.card",
  entityManagerFactoryRef = "cardEntityManagerFactory", transactionManagerRef =
  "cardTransactionManager" )
 
public class CardDataSourceConfig {


    @Bean(name = "cardDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.card")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "cardDataSource")
    public DataSource dataSource(@Qualifier("cardDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }


    @Bean(name = "cardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("cardDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.bank.model")
                .persistenceUnit("card")
                .build();
    }


    @Bean(name = "cardTransactionManager")
    public PlatformTransactionManager cardTransactionManager(
            @Qualifier("cardEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

