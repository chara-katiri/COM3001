package UserModel;

/**
 *
 * Model for User
 */
public class User {
    private String username;
    private String password;
    private String email; 

    /*
    Accessor method for  username
     */
    public String getUsername() {
        return username;
    }

    /*
    Mutator method for username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
    Accessor method for  password
     */
    public String getPassword() {
        return password;
    }

    /*
    Mutator method for  password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    Accessor method for email
     */
    public String getEmail() {
        return email;
    }

    /*
    Mutator method for email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
