package UtilsService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * Process to load the configuration file into the Properties object.
 */
@Service
public class ConfigurationReader {

    /**
     * Method to load the properties file. 
     * Parameter 'resourceLocation' is used to identify the location of properties file
     */
    
      public static Properties loadConfigurationFile(String resourceLocation) {
        //the line just below might not be needed.
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceLocation);
        Properties prop = new Properties();
        
        try {
            prop.load(inputStream);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationReader.class.getName()).log(Level.SEVERE, "Property file '" + Constants.CONFIG_FILENAME + "' not found in classpath", ex);
        }
        
        // the return statement will return the properties file 
        return prop;
    }
  
}
