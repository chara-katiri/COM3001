package Controller;

import ItemsModel.BaseItem;
import ItemsModel.BookItem;
import ItemsModel.IItem;
import UtilsService.DatabaseConnection;
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

    /*
     *Request Mapping to the database and retrive all the items currently stored in it. 
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<IItem> getItems() throws SQLException {
        String queryItems = "SELECT * FROM items;";
        return constructItemsList(queryItems);
    }

    /*
     *Request Mapping to the database and retrive only the items that the currently logged in user set as favourites. 
     */
    @RequestMapping(value = "/favourites", method = RequestMethod.GET)
    @ResponseBody

    /*
     *Get the user details autowired using Principal principal. Collect the details of the user currently logged in. 
     */
    public List<IItem> getItemsFavourites(Principal principal) throws SQLException {
        String queryFavourites = "SELECT i.ItemsID, i.Title, i.Description, i.Price, i.Category "
                + "FROM items i "
                + "INNER JOIN user_favourites uf "
                + "ON f.ItemsID = uf.Favourite JOIN users u "
                + "ON uf.username = u.username "
                + "WHERE u.username = ?";

        List<IItem> items = new ArrayList<>();

        /*
         *check that a user is currenlty logged in and return his name and favourites as a list
         */
        if (principal != null) {
            String username = principal.getName();
            items = constructItemsList(queryFavourites, username);
        }

        return items;//Return the list of items 
    }

    @Secured("ROLE_ADMIN") // Process for admins. Allow admins to manage items on the "manage" items page
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

    @Secured("ROLE_ADMIN")    //Process to allow admins remove items from the database. Parameter 'item' is the item to be removed.  

    @RequestMapping(value = "/manage/remove", method = RequestMethod.POST)
    public String removeItem(int itemId) {

        String removeFavourite = "DELETE FROM items WHERE ItemsID=?";

        dbConnection.updateDB(removeFavourite, itemId);

        return "admin";
    }

    /*
     *Create a list that contains all the items that need to be returned to the user. 
     *Parameter 'sqlQuery' defines the items that need to be returned. 
     *Parameter 'params' defines the parameters for the SQL query.
     */
    private List<IItem> constructItemsList(String sqlQuery, Object... params) {
        List<IItem> items = new ArrayList<>();
        List<Map<String, Object>> res = dbConnection.queryDB(sqlQuery, Arrays.asList("itemsID", "Title", "Description", "Price", "Category"), params);

        while (r.next()) {//the loop will return the list of items to the user
            Map<String, Object> res = new HashMap<>();// create a HashMap
            ArrayList arrayList = mapList.get(key); //get the value from the HashMap againt the input key
            arrayList.add(items);//add the items in the list
            mapList.put(key, arraylist); //put the arrayList against the key value 

            /*List<String> items = new List<String>();
             items = res.values(); // by using 'values()'  the  method will return a list that containes all the values listed in the map. https://www.salesforce.com/us/developer/docs/apexcode/Content/apex_methods_system_map.html
             items.addItems (itemsID, title, decription, price, category);
             }*/
        }
        return items;

    }
