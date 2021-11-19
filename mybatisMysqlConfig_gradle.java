
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.*.fo.**.mapper.mysql", sqlSessionFactoryRef = "mySqlSessionFactory")
public class MybatisMySqlConfig {

    @Primary //두개만들때
    @Bean("mySqlDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.mysql")
    public DataSource mysqlDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "mySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("mySqlDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/mysql/**/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "mySqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
            @Qualifier("mySqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
