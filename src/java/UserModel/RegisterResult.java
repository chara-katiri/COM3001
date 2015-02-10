package UserModel;

/**
 * Enum: When trying to register a user the following results will be available
 */
public enum RegisterResult {
    
    /**
    * Successful registration of user
    */
    SUCESS, 
    
    /**
    * Error, user already exists in the database
    */
    USER_ALREADY_EXISTS,
    
    /**
    * Error when attempt to register a new user
    */
    GENERAL_ERROR,
    
    /**
    * Fatal error when attempt to register a new user
    */
    FATAL_ERROR
}
