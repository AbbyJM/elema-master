package com.abby.elema.mybatis;

import com.abby.elema.util.LogUtil;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author: Abby
 */
@Configuration
public class SqlSessionConfig {

    @Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Interceptor interceptor=new PageInterceptor();
        Interceptor[] plugins=new Interceptor[1];
        plugins[0]=interceptor;
        sqlSessionFactoryBean.setPlugins(plugins);
        try {
            //reconfigure the mapper locations here
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"));
        } catch (IOException e) {
            LogUtil.info("error while configuring the SqlSessionFactory "+e.getMessage());
        }
        return sqlSessionFactoryBean;
    }
}
