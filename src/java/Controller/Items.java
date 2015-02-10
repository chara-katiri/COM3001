package Controller;

import items.model.item.BaseItem;
import items.model.item.IItem;
import items.model.item.Location;
import items.service.utils.DatabaseConnection;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/items")
public class Items {

    @Autowired
    private DatabaseConnection dbConnection;

    //  Request Mapping to the database and retrive all the items currently stored in it. 
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IItem> getItems() {
        String queryItems = "SELECT * FROM items;";
        return constructItemsList(queryItems);
    }

    //  Request Mapping to the database and retrive only the items that the currently logged in user set as favourites. 
    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    @ResponseBody

    //Get the user details autowired using Principal principal. Collect the details of the user currently logged in. 
    public List<IItem> getItemsFavourites(Principal principal) {
        String queryFavourites = "SELECT i.ItemsID, i.Title, i.Description, i.Price, i.Category "
                + "FROM items i "
                + "INNER JOIN user_favourites uf "
                + "ON f.ItemsID = uf.Favourite JOIN users u "
                + "ON uf.username = u.username "
                + "WHERE u.username = ?";

        List<IItem> items = new ArrayList<>();

        // check that a user is currenlty logged in and return his name and favourites as a list
        if (principal != null) {
            String username = principal.getName();
            items = constructItemsList(queryFavourites, username);
        }

        //Return the list of items 
        return items;
    }

    // Process for admins. Allow admins to manage items on the "manage" items page
    @Secured("ROLE_ADMIN")
    // Request mapping to the manage page. The manage page will return the names of the items that can be managed by admins.
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String manageItems() {
        return "admin";
    }

    //Process to allow admins add new items in the database. Parameter 'item' is the item to be added 
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manage/add", method = RequestMethod.POST)
    public String addItem(BaseItem item) {

        String insertItem = "INSERT INTO items (Title, Description, Price, Category) VALUES (?, ?, ?, ?);";

        dbConnection.executeSQL(insertItem,
                item.getTitle(), item.getDescription(), item.getPrice(), item.getCategory());

        return "admin";
    }

    //Process to allow admins remove items from the database. Parameter 'item' is the item to be removed.  
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/manage/remove", method = RequestMethod.POST)
    public String removeItem(int itemId) {

        String removeFavourite = "DELETE FROM items WHERE ItemsID=?";

        dbConnection.updateDB(removeItem, itemId);

        return "admin";
    }

    //Create a list that contains all the items that need to be returned to the user. 
    //Parameter 'sqlQuery' defines the items that need to be returned. 
    //Parameter 'params' defines the parameters for the SQL query.
    private List<IItem> constructItemsList(String sqlQuery, Object... params) {
        List<IItem> items = new ArrayList<>();

        List<Map<String, Object>> res = dbConnection.queryDB(sqlQuery, Arrays.asList("itemsID", "Title", "Description", "Price", "Category"), params);

        //the loop will return the list of items to the user
        for (Map<String, Object> r : res) {

            int id = (int) r.get("itemsID ");
            String title = (String) r.get("Title");
            String description = (String) r.get("Decription");
            int price = (int) r.get("Price");
            String category = (String) r.get("Category");
            Map flags = null;

        }
        items.addNewItem(id, name, flags
        ) 
    );
    }
}

        

} catch (SQLException ex) {Logger.getLogger(LoginController.class  

.getName()).log(Level.SEVERE, "Error executing SQL query", ex);
        } 

catch (NullPointerException ex) {Logger.getLogger(LoginController.class  

.getName()).log(Level.SEVERE, "NullPointer from DB connection still not properly initialised", ex);
        }
        return items;
    }
}