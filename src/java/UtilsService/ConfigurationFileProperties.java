package UtilsService;

import java.util.Properties;

/**
 * File Reader properties: singleton implementation
 */
public class ConfigurationFileProperties {

    private static class ConfigurationFilePropertiesHolder {

        private static final ConfigurationFileProperties INSTANCE = new ConfigurationFileProperties();

    }

    private final Properties prop;

    private ConfigurationFileProperties() {
        prop = ConfigurationReader.loadConfigurationFile(Constants.RESOURCES_PATH + Constants.CONFIG_FILENAME);
    }

    /**
     * Configuration that returns the singleton instance of the class
     */
    public static ConfigurationFileProperties getInstance() {
        return ConfigurationFilePropertiesHolder.INSTANCE;
    }

    /**
     * Accessor for the values of the configuration file.
     * Parameter 'key' is used to retrieve the key.       
     */
    public String getPropertyValue(String key) {
        
        //the return statement will return the value of the key. If the properties file doen't exist it will return an empty string. 
        return prop != null ? prop.getProperty(key) : ApplicationConstants.EMPTY_STRING;
    }

}
