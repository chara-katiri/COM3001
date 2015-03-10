package SecurityService;

import UtilsService.ConfigurationFileProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Configuration for application settings
 */
@Configuration
@EnableWebMvc

public class ApplicationConfig {

/**
 * Creation of MySQL database to hold client's data using Spring Security 
 */
        @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        ConfigurationFileProperties config = ConfigurationFileProperties.getInstance();

        source.setDriverClassName(config.getPropertyValue("dbDriver"));
        source.setUrl(config.getPropertyValue("dbUrl") + config.getPropertyValue("dbName"));
        source.setUsername(config.getPropertyValue("dbUserName"));
        source.setPassword(config.getPropertyValue("dbPassword"));

        //The return statement will return the data source established with configuration properties
        return source;
    }
}


