package mike.samples.spark.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
class ContainerConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource datasource) {
        var jdbc = new JdbcTemplate(datasource);
        jdbc.setFetchSize(5000);
        return jdbc;
    }
}
