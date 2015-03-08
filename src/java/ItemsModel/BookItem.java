
package ItemsModel;

import java.util.Map; 

/**
 * Implement IItems for category 'BOOK' 
 */
public class BookItem extends BaseItem{


/**
 * Constructor for BookItem. Since BookItem extends BaseItem, 'super' is used to access  the BaseItem class instance. 
 */
    public BookItem(int itemsID, String title, String description, double price, String category) {
        super(itemsID, title, description, price, category);
    }
    
}
