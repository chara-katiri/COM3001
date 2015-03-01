
package ItemsModel;
import java.util.Map; 

/**
 * Implement IItems for category 'ROOM'
 */
public class RoomItem extends BaseItem{

    /**
 * Constructor for RoomItem. Since RoomItem extends BaseItem, 'super' is used to access  the BaseItem class instance. 
 */
    public RoomItem(int itemsID, String title, String description, double price, enum category) {
        super(itemsID, title, description, price, category);
    }
    
}
