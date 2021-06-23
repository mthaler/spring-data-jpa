package com.mthaler.springdatajpa.config

import org.apache.commons.dbcp2.BasicDataSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = ["com.mthaler.springdatajpa"])
@EnableJpaRepositories(basePackages = ["com.mthaler.springdatajpa.repos"])
@PropertySource("classpath:db/jdbc.properties")
class DataJpaConfig {

    @Value("\${driverClassName}")
    private val driverClassName: String? = null

    @Value("\${url}")
    private val url: String? = null

    @Value("\${username}")
    private val username: String? = null

    @Value("\${password}")
    private val password: String? = null

    @Bean
    fun dataSource(): DataSource {
        return try {
            val dataSource = BasicDataSource()
            dataSource.driverClassName = driverClassName
            dataSource.url = url
            dataSource.username = username
            dataSource.password = password
            dataSource
        } catch (e: Exception) {
            logger.error("DBCP DataSource bean cannot be created!", e)
            throw e
        }
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager? {
        return JpaTransactionManager(entityManagerFactory())
    }

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter? {
        return HibernateJpaVendorAdapter()
    }

    @Bean
    fun hibernateProperties(): Properties {
        val hibernateProp = Properties()
        hibernateProp["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQL9Dialect"
        hibernateProp["hibernate.hbm2ddl.auto"] = "create-drop"
        hibernateProp["hibernate.format_sql"] = true
        hibernateProp["hibernate.use_sql_comments"] = true
        hibernateProp["hibernate.show_sql"] = true
        hibernateProp["hibernate.max_fetch_depth"] = 3
        hibernateProp["hibernate.jdbc.batch_size"] = 10
        hibernateProp["hibernate.jdbc.fetch_size"] = 50
        return hibernateProp
    }

    @Bean
    fun entityManagerFactory(): EntityManagerFactory {
        val factoryBean = LocalContainerEntityManagerFactoryBean()
        factoryBean.setPackagesToScan("com.mthaler.springdatajpa")
        factoryBean.dataSource = dataSource()
        factoryBean.setJpaProperties(hibernateProperties())
        factoryBean.jpaVendorAdapter = jpaVendorAdapter()
        factoryBean.afterPropertiesSet()
        return factoryBean.nativeEntityManagerFactory
    }

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(DataJpaConfig::class.java)
    }
}