package UtilsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Creation of PreparedStatements for SQL string statements
 * Parameter 'sqlString' is used as SQL statement to create PreparedStatements 
 * Parameter 'params' is used as the parameters of the statement.
 */
public class Utils {
    
    /**
     * Utility methods. In order to hash the password the BCryptPasswordEncoder is used. 
     * Parameter 'password' is used as the password to be encoded.
     */
        public static String encodePassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        //the return statement returns the password hashed. 
        return passwordEncoder.encode(password);
    }

}

