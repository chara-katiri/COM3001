package Controller;

import UtilsService.DatabaseConnection;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




/**@RequestMapping ("favourites")
@Configuration 
@ComponentScan ("UtilsService") **/

/* 
 * Controller for favourites
 */
@Controller
public class Favourites {

    
    @Autowired
    DatabaseConnection dbConnection;

    /*
     * Request mapping to favourites page and return the favourites name
     */
    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    public String favourites() {
        return "favourites";
    }

    /*
     * Allow the user to bookmark an item as favourite. 
     */
    @RequestMapping(value = "/addToFavourites", method = RequestMethod.GET)
    @ResponseBody

    /*
     *  Get the user details autowired using Principal principal. Collect the details of the user currently logged in. Also collect the item ID in order to add it to user’s favourites
     */
    public boolean addToFavourites(Principal principal, @RequestParam(value = "item") int itemId) {
        dbConnection = DatabaseConnection.getInstance();

        /*
         *The principal is valid if the user is logged in 
         */
        if (principal != null) {
            String addFavourite = "INSERT INTO user_favourites (username, favourite) VALUES (?, ?);";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, itemId);

            /*
             *the return statements show whether the statement has been executed
             */
            return true;
        }

        return false;
    }

    /*
     * Process to remove an item from the list of favourites
     */
    @RequestMapping(value = "/removeFromFavourites", method = RequestMethod.GET)
    @ResponseBody

    /*
     *Get the user details autowired using Principal principal. Collect the details of the user currently logged in. Also collect the item ID in order to remove it from user’s favourites
     */
    public boolean removeFromFavourites(Principal principal, @RequestParam(value = "item") int itemId) {
        dbConnection = DatabaseConnection.getInstance();

        /*
         *If the user is logged in then principal is valid
         */
        if (principal != null) {
            String addFavourite = "DELETE FROM user_favourites WHERE username=? AND favourite=?;";
            String username = principal.getName();

            dbConnection.updateDB(addFavourite, username, itemId);

            /*
             *the return statements show whether the statement has been executed  
             */
            return true;
        }

        return false;
    }

    /*
     *Process to check whether item X is already marked as favourite by the current user
     */
    @RequestMapping(value = "/isFavourite", method = RequestMethod.GET)
    @ResponseBody

    /*
     *Get the user details autowired using Principal principal. Collect the details of the user currently logged in.   Also get the ID of the item to check it.  
     */
    public boolean isFavourite(Principal principal, @RequestParam(value = "item") int itemId) {
        dbConnection = DatabaseConnection.getInstance();

        /*
         *If the user is logged in then principal is valid
         */
        if (principal != null) {
            String addFavourite = "SELECT username, favourite FROM user_favourites WHERE username = ? AND favourite = ?";
            String username = principal.getName();
            
        try {
            List<Map<String, Object>> res= dbConnection.queryDB(addFavourite, Arrays.asList("username", "favourite"), username, itemId);
            if (res.size() >0){
                return true;
            }
            } catch (SQLException ex) {
                Logger.getLogger(Favourites.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*
         *the return statement confirms whether the item is a favourite or not
         */
        return false;
    }
}
