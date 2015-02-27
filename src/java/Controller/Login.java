package Controller;

import UserModel.RegisterResult;
import UserModel.User;
import UtilsService.DatabaseConnection;
import UtilsService.Utils;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//Controller for login 
@Controller
public class Login {

    @Autowired
    private DatabaseConnection dbConnection;

    
    //Request mapping to login page and retrieve the model and view name. 
    //Parameter 'error' is returned if the user attempts to login but the credentials submitted are invalid. 
    //Parameter 'error' is an empty string. 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password");
        }

        //Parameter 'logout' can be used if the user is already logged in. Parameter 'logout' is an empty string. 
        if (logout != null) {
            model.addObject("logout", "Logout Successful.");
        }
        model.setViewName("login");

        // The return statement will return the ModelAndView for the login page along with appropriate messages. 
        return model;
    }

   
    //Request mapping to the register page. 
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        
        //The return statement will return the file name of the register page.  
        return "register";
    }

 
    //Request mapping to the 'register' page and use the POST method to register a new user.
    //Parameter 'user' is the new user to be registered. 
    //Parameter 'model' is used to display the register result message on result page.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, ModelMap model) {
        dbConnection = DatabaseConnection.getInstance();

        RegisterResult registerResult = tryRegister(user);

        //Once the user is registered naviaget to result page. 
        String result;
        switch (registerResult) {
            case SUCCESS:
                result = "success";
                break;
            case USER_ALREADY_EXISTS:
                result = "alreadyexists";
                break;
            case GENERAL_ERROR:
            case FATAL_ERROR:
            default:
                result = "error";
                break;
        }

        model.addAttribute("result", result);

        //The return statement will return the file name of the register page.  
        return "result";
    }


    //Process to register result and try to register the new user.
    //Parameter 'user' is the user that the system will attempt to register. 
    private RegisterResult tryRegister(User user) {
        RegisterResult registerResult = RegisterResult.FATAL_ERROR;

        if (userAlreadyExists(user)) {
            registerResult = RegisterResult.USER_ALREADY_EXISTS;
        } else {
            String hashedPassword = Utils.encodePassword(user.getPassword());
            String insertUser = "INSERT INTO users (username, password, enabled) VALUES (?,?,1);";
            String insertRole = "INSERT INTO user_roles (username, ROLE) VALUES (?, 'ROLE_USER');";

            try {
                boolean success = dbConnection.executeSQL(insertUser, user.getUsername(), hashedPassword);
                success = success && dbConnection.executeSQL(insertRole, user.getUsername());

                if (success) {
                    registerResult = RegisterResult.SUCCESS;
                } else {
                    registerResult = RegisterResult.GENERAL_ERROR;
                }
            } catch (NullPointerException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
            }
        }

        //The return statement will return the result of the attempt to register the user 
        return registerResult;
    }


    //Search in the database to cofirm if the has already exists. 
    //Parameter 'user' is used to query the user. The method returns the user if already exists. 
    private boolean userAlreadyExists(User user) {
        String queryUser = "SELECT username, password, enabled FROM users WHERE username = ? ";
        try {
            List<Map<String, Object>> res = dbConnection.queryDB(queryUser, Arrays.asList("username", "password", "enabled"), user.getUsername());
            return res.size() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

