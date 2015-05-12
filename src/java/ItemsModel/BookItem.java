
package ItemsModel;

import java.util.Map; 

/**
 * Implement IItems for  'ROOMS', 'BOOKS'
 */
public class BookItem extends BaseItem{

    /**
 * Constructor for items. Since RoomItem extends BaseItem, 'super' is used to access  the BaseItem class instance. 
 */
   /** public ConstructItems(int itemsID, String title, String description, double price, String category) {
        super(itemsID, title, description, price, category);
    }
     * @param id
     * @param title
     * @param description
     * @param price
     * @param category
     * @param flags */

    public BookItem(int id, String title, String description, double price, String category, Map flags) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super (ItemType.BOOKS, id, title, description, price, category);

    }
    
}
