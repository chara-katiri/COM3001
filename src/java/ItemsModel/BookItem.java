
package ItemsModel;

import java.util.Map; 

/**
 * Implement IItems for  'ROOMS', 'BOOKS'
 */
public class BookItem extends BaseItem{

    public BookItem(int id, String title, String description, double price, String category, Map flags) {
        super(ItemType.BOOKS, id, title, description, price, category);
    }
 
}
