
package ItemsModel;
import java.util.Map; 

/**
 * Implement IItems for category 'ROOM'
 */
public class Items extends BaseItem{

    /**
 * Constructor for RoomItem. Since RoomItem extends BaseItem, 'super' is used to access  the BaseItem class instance. 
 */
    public Items(int itemsID, String title, String description, double price, String category) {
        super(itemsID, title, description, price, category);
    }
    
}
