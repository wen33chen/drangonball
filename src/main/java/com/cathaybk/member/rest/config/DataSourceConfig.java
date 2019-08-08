package com.cathaybk.member.rest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.cathaybk.encrypt.Decrypter;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataSourceConfig {

    @Value("${oracle.db.url}")
    private String dataSourceUrl;

    @Value("${oracle.db.username}")
    private String userName;

    @Value("${oracle.db.password}")
    private String userPassWord;

    @Bean(name = "PROD_DB")
    @Primary
    @Profile("prod")
    public DataSource getProdDataSource() throws Exception {
        String[] dbInfo = Decrypter.getPassword("PDRHCTCOMRACL03_1521_OINVINST_IVTLXIVP01PLFM", "OINVPLFM");

        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();

        poolDataSource.setUser(dbInfo[0]);
        poolDataSource.setPassword(dbInfo[1]);
        poolDataSource.setURL(dataSourceUrl);
        poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        poolDataSource.setFastConnectionFailoverEnabled(true);
        poolDataSource.setMinPoolSize(30); // 開個30?
        poolDataSource.setMaxPoolSize(100); // 之前說最大連線是400 or 500?
        poolDataSource.setValidateConnectionOnBorrow(true); // boolean, 放true
        poolDataSource.setSQLForValidateConnection("select user from dual");

        return poolDataSource;
    }

    @Bean(name = "UAT_DB")
    @Primary
    @Profile("uat")
    public DataSource getUatDataSource() throws Exception {
        String[] dbInfo = Decrypter.getPassword("TDRHCTCOMRACL01_1521_OINVINST_IVTLXIVP01PLFM", "OINVPLFM");

        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        poolDataSource.setUser(dbInfo[0]);
        poolDataSource.setPassword(dbInfo[1]);
        poolDataSource.setURL(dataSourceUrl);
        poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        poolDataSource.setFastConnectionFailoverEnabled(true);
        poolDataSource.setMinPoolSize(100); // 開個30?
        poolDataSource.setMaxPoolSize(200); // 之前說最大連線是400 or 500?
        poolDataSource.setValidateConnectionOnBorrow(true);
        poolDataSource.setSQLForValidateConnection("select user from dual");

        return poolDataSource;
    }

    @Bean(name = "UT_DB")
    @Primary
    @Profile("ut")
    public DataSource getUtDataSource() throws Exception {
        String[] dbInfo = Decrypter.getPassword("DDRHAML01_1521_OINVINST_IVTLXIVP01PLFM", "OINVPLFM");

        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        poolDataSource.setUser(dbInfo[0]);
        poolDataSource.setPassword(dbInfo[1]);
        poolDataSource.setURL(dataSourceUrl);
        poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        poolDataSource.setFastConnectionFailoverEnabled(true);
        poolDataSource.setValidateConnectionOnBorrow(true);
        poolDataSource.setSQLForValidateConnection("select user from dual");

        return poolDataSource;
    }

    @Bean(name = "LOCAL_DB")
    @Primary
    @Profile("local")
    public DataSource getLocalDataSource() throws Exception {

        PoolDataSource poolDataSource = PoolDataSourceFactory.getPoolDataSource();
        poolDataSource.setUser(userName);
        poolDataSource.setPassword(userPassWord);
        poolDataSource.setURL(dataSourceUrl);
        poolDataSource.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
        poolDataSource.setFastConnectionFailoverEnabled(true);
        poolDataSource.setValidateConnectionOnBorrow(true);
        poolDataSource.setSQLForValidateConnection("select user from dual");

        return poolDataSource;
    }
}
